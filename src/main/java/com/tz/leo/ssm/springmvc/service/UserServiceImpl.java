package com.tz.leo.ssm.springmvc.service;

import cn.hutool.core.util.RandomUtil;
import com.tz.leo.ssm.springmvc.dao.UserDao;
import com.tz.leo.ssm.springmvc.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Author: tz_wl
 * Date: 2020/7/7 9:10
 * Content:
 */

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void save(User user) {
        user.setId(RandomUtil.randomInt(100, 10000));
        userDao.save(user);
    }

    // 增删改默认级别是  Propagation.REQUIRED 类型的  查询是supports 级别的
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public boolean isExist(User user) {
        User u =  userDao.isExist(user);
        boolean bReturn=false;
        if(!u.equals(null)){
            bReturn=true;
        }
        return bReturn;
    }
}
