package com.tz.leo.ssm.springmvc.controller;

import com.alibaba.fastjson.JSONObject;
import com.tz.leo.ssm.springmvc.entity.User;
import com.tz.leo.ssm.springmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: tz_wl
 * Date: 2020/7/9 8:05
 * Content:
 */
@RequestMapping("userAjax")
@Controller
public class UserAjaxController {

    @Autowired
    UserService userService;


    /**
    *@Description: 传统方式 返回参数类型void ，使用 HttpServletResponse 回传数据到页面
     *             引用alibaba 的 fastJson 序列化实体为 json 字符串
    *@Param:
    *@return:
    *@date: 2020/7/9
    */
    @RequestMapping("findAll")
    public void findAll(HttpServletResponse response) throws IOException {
        List<User> userList = userService.findAll();
        String str = JSONObject.toJSONString(userList);
        response.setContentType("application/json");
        response.getWriter().println(str);
    }
    
    /**
    *@Description: 使用spring mvc提供的 org.springframework.web.bind.annotation.ResponseBody 封装返回结果
     *              注意此处的序列化是@ResponseBody自动帮我们做的，默认用 jackson序列化数据
     *      @ResponseBody注解在转换json时使用jackson依赖
    <dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.9.0</version>
    </dependency>

     如果不加上面的jackson依赖，将会显示  No converter found for return value of type: class java.util.ArrayList 错误
    *@Param: 
    *@return: 
    *@date: 2020/7/9
    */
    @RequestMapping("findAll2_1")
    @ResponseBody
    public List<User> findAll2_1(){
        List<User> userList = userService.findAll();
        return userList;
    }
    
    /**
    *@Description: 方式同上面的方法 区别是@Response修饰方法，还是修饰返回值 
    *@Param: 
    *@return: 
    *@date: 2020/7/9
    */
    @RequestMapping("findAll2_2")
    public  @ResponseBody List<User> findAll2_2(){
        List<User> userList = userService.findAll();
        return userList;
    }

}
