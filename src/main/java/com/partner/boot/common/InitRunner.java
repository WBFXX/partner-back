package com.partner.boot.common;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.http.HttpUtil;
import com.partner.boot.entity.User;
import com.partner.boot.mapper.UserMapper;
import com.partner.boot.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
public class InitRunner implements ApplicationRunner {
    @Resource
    UserMapper userMapper;
    /**
     * 在项目启动成功之后会运行这个方法
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        try {
            //帮我在项目启动的时候查一次数据库，防止数据库的懒加载
            userMapper.select1();
            log.info("数据库连接查询成功，启动项目");
            //发送一次异步web请求，来初始化Tomcat连接
            ThreadUtil.execAsync(
                    () -> {
                        HttpUtil.get("http://localhost:9090/");
                        log.info("启动项目tomcat连接查询成功");
                    }
            );
        } catch (Exception e) {
            log.warn("启动优化失败", e);
        }
    }


}
