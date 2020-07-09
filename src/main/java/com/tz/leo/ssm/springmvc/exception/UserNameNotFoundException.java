package com.tz.leo.ssm.springmvc.exception;

/**
 * //自定义用户名不存的异常
 * Author: tz_wl
 * Date: 2020/7/9 10:13
 * Content:
 */
public class UserNameNotFoundException extends RuntimeException {

    public UserNameNotFoundException(String message) {
        super(message);
    }
}
