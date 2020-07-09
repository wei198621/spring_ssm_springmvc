package com.tz.leo.ssm.springmvc.controller;

import com.tz.leo.ssm.springmvc.entity.User;
import com.tz.leo.ssm.springmvc.exception.UserNameNotFoundException;
import com.tz.leo.ssm.springmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Author: tz_wl
 * Date: 2020/7/9 10:19
 * Content:
 */

@RequestMapping("excepTest")
@Controller
public class ExceptionTestController {
    @Autowired
    private UserService userService;


    /**
    *@Description: 正常访问
    *@Param:
    *@return:
    *@date: 2020/7/9
    */
    @RequestMapping("findAll")
    public  String findAll01(HttpServletRequest request){
        List<User> users = userService.findAll();
        request.setAttribute("user",users);
        return "findAll";
    }


    /**
    *@Description: UserNameNotFoundException  主动弹出异常1
    *@Param:
    *@return:
    *@date: 2020/7/9
    */
    @RequestMapping("findAll02")
    public  String findAll02(HttpServletRequest request){
        List<User> users = userService.findAll();
        request.setAttribute("user",users);
        throw new UserNameNotFoundException("UserNameNotFoundException");
        //return "findAll";
    }


    /**
    *@Description:   IllegalStateException   主动弹出异常2
    *@Param:
    *@return:
    *@date: 2020/7/9
    */

    @RequestMapping("findAll03")
    public  String findAll03(HttpServletRequest request){
        List<User> users = userService.findAll();
        request.setAttribute("user",users);
        throw new RuntimeException("RuntimeException");
        //return "findAll";
    }
}

