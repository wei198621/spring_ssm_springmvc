package com.tz.leo.ssm.springmvc.interceptors;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Author: tz_wl
 * Date: 2020/7/9 9:17
 * Content:
 *
 * 1.请求经过拦截器会优先进入拦截器中preHandler方法执行preHandler方法中内容
 * 2.如果preHandler 返回为true 代表放行请求  如果返回值为false 中断请求
 * 3.如果preHandler返回值为true,会执行当前请求对应的控制器中方法
 * 4.当控制器方法执行结束之后,会返回拦截器中执行拦截器中postHandler方法
 * 5.posthanlder执行完成之后响应请求,在响应请求完成后会执行afterCompletion方法
 *
 */
public class MyInterceptor implements HandlerInterceptor {

    /*
    参数1: 当前请求对象 参数2:当前请求对应响应对象  参数3:当前请求的控制器对应的方法对象
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        System.out.println( ((HandlerMethod)o).getMethod().getName());
        System.out.println("===========1=============");
        //   //强制用户登录
        Object user =httpServletRequest.getSession().getAttribute("user");
        if(user==null){
            //重定向到登录页面
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+"/login.html");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

        System.out.println(modelAndView);
        System.out.println("===========3=============");

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

        if(e!=null){
            System.out.println(e.getMessage());
        }
        System.out.println("===========4=============");
    }
}
