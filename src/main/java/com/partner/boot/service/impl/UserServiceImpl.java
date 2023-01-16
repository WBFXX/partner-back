package com.partner.boot.service.impl;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.partner.boot.common.Constants;
import com.partner.boot.entity.User;
import com.partner.boot.exception.ServiceException;
import com.partner.boot.mapper.UserMapper;
import com.partner.boot.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.SneakyThrows;
import net.bytebuddy.utility.RandomString;
import org.springframework.stereotype.Service;

import java.rmi.ServerException;
import java.util.Date;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 计科1901武泊帆
 * @since 2022-12-30
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {


    @Override
    public User login(User user) {
        User dbUser = null;
        try {
            dbUser = getOne(new UpdateWrapper<User>().eq("username", user.getUsername()));
        } catch (Exception e) {
            throw new RuntimeException("数据库异常");
        }

        if (dbUser == null) {
            throw new ServiceException("用户名错误");
        }
        if (!user.getPassword().equals(dbUser.getPassword())) {
            throw new ServiceException("用户名或密码错误");
        }
        return dbUser;
    }

    @Override
    public User register(User user) {

        try {
            //存储用户信息
            return saveUser(user);
        } catch (Exception e) {
            throw new RuntimeException("数据库异常");
        }
    }


    private User saveUser(User user){
        User dbUser = getOne(new UpdateWrapper<User>().eq("username", user.getUsername()));
        if (dbUser != null) {
            throw new ServiceException("用户已存在");
        }
        //设置昵称
        if (StrUtil.isBlank(user.getName())) {
//               String name = Constants.USER_NAME_PREFIX + DateUtil.format(new Date(),Constants.DATE_RULER_YYYYMMDD)
//                       + RandomUtil.randomString(4); //随机昵称，看common里的Constants
            user.setName("李白");
        }
        if (StrUtil.isBlank(user.getPassword())) {
            user.setPassword("123");//设置密码
        }
        //设置唯一标识
        user.setUid(IdUtil.fastSimpleUUID());

        boolean saveSuccess = save(user);
        if (!saveSuccess) {
            throw new RuntimeException("注册失败");
        }
        return user;
    }
}
