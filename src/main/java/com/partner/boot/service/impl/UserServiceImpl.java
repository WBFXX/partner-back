package com.partner.boot.service.impl;

import cn.dev33.satoken.secure.BCrypt;
import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.partner.boot.common.Constants;
import com.partner.boot.common.enums.EmailCodeEnum;
import com.partner.boot.common.enums.LoginDTO;
import com.partner.boot.controller.domain.UserRequest;
import com.partner.boot.entity.User;
import com.partner.boot.exception.ServiceException;
import com.partner.boot.mapper.UserMapper;
import com.partner.boot.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.partner.boot.utils.EmailUtils;
import com.partner.boot.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 计科1901武泊帆
 * @since 2022-12-30
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {


    //    StringRedisTemplate stringRedisTemplate;//官方提供stringRedisTemplate专门用来做redis字符串存储的
    //key是code， value是时间戳
//    private static final Map<String, Long> CODE_MAP = new ConcurrentHashMap<>();
    private static final long TIME_IN_MS5 = 5 * 60 * 1000;//表示5分钟毫秒数

    @Autowired
    EmailUtils emailUtils;


    @Override
    public LoginDTO login(UserRequest user) {
        User dbUser = null;
        try {
            //校验邮箱
            dbUser = getOne(new UpdateWrapper<User>().eq("username", user.getUsername()).or().eq("email", user.getUsername()));
        } catch (Exception e) {
            throw new RuntimeException("数据库异常");
        }

        if (dbUser == null) {
            throw new ServiceException("用户名错误");
        }
//        String securePass = SaSecureUtil.aesEncrypt(Constants.PASSWORD_KEY, user.getPassword());
//        if (!securePass.equals(dbUser.getPassword())) {
//            throw new ServiceException("用户名或密码错误");
//        }
        if (!BCrypt.checkpw(user.getPassword(), dbUser.getPassword())) {
            throw new ServiceException("用户名或密码错误");
        }
        //登录
        StpUtil.login(dbUser.getUid());
        StpUtil.getSession().set(Constants.LOGIN_USER_KEY, dbUser);
        String tokenValue = StpUtil.getTokenInfo().getTokenValue();
//        LoginDTO loginDTO = new LoginDTO(dbUser, tokenValue);
        return LoginDTO.builder().user(dbUser).token(tokenValue).build();
    }


    @Override
    public void register(UserRequest user) {
        // 校验邮箱
        String key = Constants.Email_CODE + EmailCodeEnum.REGISTER.getValue() + user.getEmail();
        validateEmail(key, user.getEmailCode());
        try {

            User saveUser = new User();
            BeanUtils.copyProperties(user, saveUser);   // 把请求数据的属性copy给存储数据库的属性
            // 存储用户信息
            saveUser(saveUser);
        } catch (Exception e) {
            throw new RuntimeException("数据库异常", e);
        }
    }


    @Override
    public void spendEmail(String email, String type) {
        String emailPrefix = EmailCodeEnum.getValue(type);
        if (StrUtil.isBlank(emailPrefix)) {
            throw new ServiceException("不支持的邮箱验证类型");
        }
        //设置redis的key
        String key = Constants.Email_CODE + emailPrefix + email;
        Long expireTime = RedisUtils.getExpireTime(key);

        //4 分钟
        if (expireTime != null && expireTime > 4 * 60) {
            throw new ServiceException("发送邮箱验证过于频繁");
        }

        Integer code = Integer.valueOf(RandomUtil.randomNumbers(6));
        log.info("本次发送的验证码是:{}", code);
        String context = "<b>尊敬的用户：</b><br><br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;您好，" +
                "考研交流互助平台提醒您本次的验证码是：<b>{}</b>，" +
                "有效期5分钟。<br><br><br><b>考研交流互助平台</b>";
        String html = StrUtil.format(context, code);
        //校验邮箱是否注册
        User user = getOne(new QueryWrapper<User>().eq("email", email));
        if (EmailCodeEnum.REGISTER.equals(EmailCodeEnum.getEnum(type))) {//无需权限验证即可发送邮箱验证
            if (user != null) {
                throw new ServiceException("邮箱已注册");
            }
        } else if (EmailCodeEnum.RESET_PASSWORD.equals(EmailCodeEnum.getEnum(type))) {
            if (user == null) {
                throw new ServiceException("未找到用户");
            }
        }
        //忘记密码
        ThreadUtil.execAsync(() -> {//多线程异步请求，不管成功还是失败都会继续执行。可以防止网络阻塞
            emailUtils.sendHtml("【考研交流互助平台】验证提醒", html, email);
        });
        // CODE_MAP.put(email + code, System.currentTimeMillis());


        //设置redis缓存
        RedisUtils.setCacheObject(key, code, TIME_IN_MS5, TimeUnit.MILLISECONDS);
    }

    /**
     * 重置密码
     *
     * @param userRequest
     * @return
     */
    @Override
    public String passwordReset(UserRequest userRequest) {
        String email = userRequest.getEmail();
        User dbUser = getOne(new UpdateWrapper<User>().eq("email", email));
        if (dbUser == null) {
            throw new ServiceException("未找到用户");
        }
        //校验邮箱
        String key = Constants.Email_CODE + EmailCodeEnum.RESET_PASSWORD.getValue() + email;

        validateEmail(key, userRequest.getEmailCode());
        String newPass = "123";
        //加密新密码
//        dbUser.setPassword(SaSecureUtil.aesEncrypt(Constants.PASSWORD_KEY,newPass));
        dbUser.setPassword(BCrypt.hashpw(newPass));//BCrypt加密

//            dbUser.setPassword(newPass);
        try {
            updateById(dbUser);//设置到数据库
        } catch (Exception e) {
            throw new RuntimeException("注册失败", e);
        }
        return newPass;
    }

    /**
     * 退出登录
     * @param uid
     */
    @Override
    public void logout(String uid) {
        StpUtil.logout(uid);
        log.info("用户{}退出成功",uid);
    }

    /**
     * 校验邮箱
     *
     * @param emailCode
     */
    private void validateEmail(String emailKey, String emailCode) {
        //校验邮箱
//        String key = email + emailCode;
        Integer code = RedisUtils.getCacheObject(emailKey);
        if (code == null) {
            throw new ServiceException("验证码失效");
        }
        if (!emailCode.equals(code.toString())) {
            //说明验证码过期
            throw new ServiceException("验证码过期，请重新发送！");
        }
//        Long timestamp = CODE_MAP.get(key);
//        if (timestamp == null) {
//            throw new ServiceException("请先验证邮箱");
//        }
//        //timestamp(发送验证码时间)+5分钟＞当前时间
//        if (timestamp + TIME_IN_MS5 < System.currentTimeMillis()) {
//            //说明验证码过期
//            throw new ServiceException("验证码过期，请重新发送！");
//        }
//        CODE_MAP.remove(key);
        //  清除缓存
        RedisUtils.deleteObject(emailKey);
    }

    public User saveUser(User user) {
        User dbUser = getOne(new UpdateWrapper<User>().eq("username", user.getUsername()));
        if (dbUser != null) {
            throw new ServiceException("用户已存在");
        }
        //设置昵称
        if (StrUtil.isBlank(user.getName())) {
//               String name = Constants.USER_NAME_PREFIX + DateUtil.format(new Date(),Constants.DATE_RULER_YYYYMMDD)
//                       + RandomUtil.randomString(4); //随机昵称，看common里的Constants
            user.setName("系统用户" + RandomUtil.randomString(6));
        }
        if (StrUtil.isBlank(user.getPassword())) {
            user.setPassword("123");//设置密码
        }
        //加密用户密码
//        user.setPassword(SaSecureUtil.aesEncrypt(Constants.PASSWORD_KEY,user.getPassword()));
        user.setPassword(BCrypt.hashpw(user.getPassword()));//BCrypt加密
        //设置唯一标识
        user.setUid(IdUtil.fastSimpleUUID());
        try {
            save(user);
        } catch (Exception e) {
            throw new RuntimeException("注册失败", e);
        }
        return user;
    }
}
