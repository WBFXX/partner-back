package com.partner.boot;

import com.partner.boot.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PartnerBackApplicationTests {

    @Autowired
    IUserService userService;


    @Test
    void contextLoads() {

        userService.removeById(2);
        System.out.println(userService.getById(2));
    }

    @Test
    void insert() {

        userService.removeById(3);
        System.out.println("查询username" +
                userService.getById(3));
    }

}
