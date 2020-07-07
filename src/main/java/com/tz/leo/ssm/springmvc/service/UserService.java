package com.tz.leo.ssm.springmvc.service;

import com.tz.leo.ssm.springmvc.entity.User;

import java.util.List;

/**
 * Author: tz_wl
 * Date: 2020/7/7 9:09
 * Content:
 */
public interface UserService {
    void save(User user);
    List<User> findAll();
    boolean isExist(User user);
}
