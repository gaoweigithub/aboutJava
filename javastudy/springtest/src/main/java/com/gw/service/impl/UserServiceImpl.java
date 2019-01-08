package com.gw.service.impl;

import com.gw.dao.inter.UserDao;
import com.gw.pojo.User;
import com.gw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    public int countAll() {
        return this.userDao.countAll();
    }

    public void insertUser(User user) {
        this.userDao.insertUser(user);
    }

    public void update_insert(Map map, User user) throws Exception {
        this.userDao.updateUser(map);
        this.userDao.insertUser(user);
    }

    public void update(Map map) {
        this.userDao.updateUser(map);
    }

    public void delete(String id) throws Exception {
        this.userDao.deleteUser(id);
    }
}