package com.tz.leo.ssm.springmvc.handlerxception;

import com.tz.leo.ssm.springmvc.exception.UserNameNotFoundException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义全局异常处理类
 * Author: tz_wl
 * Date: 2020/7/9 10:10
 * Content:
 */
public class GlobalExceptionResolver implements HandlerExceptionResolver {

    /**
     *
     *
     * 用来处理发生异常时方法
     * @param request   当前请求对象
     * @param response  当前请求对应的响应对象
     * @param handler   当前请求的方法对象
     * @param ex        当前出现异常时的异常对象
     * @return          出现异常时展示视图和数据
     * @return
     */
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        System.out.println("进入全局异常处理器获取的异常信息为: "+ex.getMessage());
        ModelAndView modelAndView=new ModelAndView();
        //基于不同业务异常跳转到不同页面
        if(ex instanceof UserNameNotFoundException){
            modelAndView.setViewName("redirect:/login.html");
        }else{
            modelAndView.setViewName("redirect:/error.jsp");
        }
        modelAndView.addObject("msg",ex.getMessage());
        return  modelAndView;
    }
}
