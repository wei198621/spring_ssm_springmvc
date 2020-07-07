package com.tz.leo.ssm.springmvc.controller;

import com.tz.leo.ssm.springmvc.entity.User;
import com.tz.leo.ssm.springmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Author: tz_wl
 * Date: 2020/7/7 9:21
 * Content:
 */
@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 添加用户
     */
    @RequestMapping("save")
    public String save(User user){
        try {
            userService.save(user);
            return "redirect:/user/findAll";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/add.jsp";
        }
    }
    /**
     * 查询所有
     */
    @RequestMapping("findAll")
    public String findAll(HttpServletRequest request){
        List<User> users = userService.findAll();
        request.setAttribute("users",users);
        return "findAll";
    }

    //此方法等同于上面的方法
    //org.springframework.ui.Model  封装了
    //javax.servlet.http.HttpServletRequest
    public String findAllExt(Model model){
        List<User> users = userService.findAll();
        model.addAttribute("users",users);
        return "findAll";
    }


    @RequestMapping("isExist")
    public String isExist(HttpServletRequest request){

        User u = new User();
        u.setName(request.getParameter("name"));
        userService.isExist(u);
        return "redirect:/user/findAll";
    }

    @RequestMapping("login")
    public String login(){
        return "login";
    }


}
