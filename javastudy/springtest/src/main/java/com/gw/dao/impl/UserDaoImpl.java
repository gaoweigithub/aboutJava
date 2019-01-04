package com.gw.dao.impl;

import com.google.zxing.BinaryBitmap;
import com.gw.dao.inter.UserDao;
import com.gw.mapper.UserMapper;
import com.gw.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("userDao")
public class UserDaoImpl implements UserDao {
    @Autowired
    private UserMapper userMapper;

    public int countAll() {
        return this.userMapper.countAll();
    }

    public void insertUser(User user) {
        this.userMapper.insertUser(user);
    }

    public List<User> getAllUser() {
        return this.userMapper.getAllUser();
    }

    public User getById(String id) {
        return this.userMapper.getById(id);
    }

    public void deleteUser(String id) {
        this.userMapper.deleteUser(id);
    }

    public void updateUser(Map<String, Object> map) {
        this.userMapper.updateUser(map);
    }
}