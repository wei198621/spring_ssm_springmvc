package com.tz.leo.ssm.springmvc.dao;

import com.tz.leo.ssm.springmvc.entity.User;

import java.util.List;

/**
 * Author: tz_wl
 * Date: 2020/7/7 9:07
 * Content:
 */
public interface UserDao {
     void save(User user);
    List<User> findAll();
    User isExist(User user);
}
