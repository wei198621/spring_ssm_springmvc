package com.tz.leo.test;

import com.tz.leo.ssm.springmvc.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Author: tz_wl
 * Date: 2020/7/7 11:28
 * Content:
 */
public class TestUserService {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        UserService userService = (UserService) context.getBean("userService");
        userService.findAll().forEach(user-> System.out.println("user"+user));
    }
}
