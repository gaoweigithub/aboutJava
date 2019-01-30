package com.gw.dao.impl;

import com.gw.dao.inter.UserDao;
import com.gw.mapper.UserMapper;
import com.gw.model.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service("userDao")
public class UserDaoImpl implements UserDao {
    @Autowired
    private UserMapper userMapper;

    public int countAll() {
        return this.userMapper.countAll();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void insertUser(User user) {
        this.userMapper.insertUser(user);
    }

    public List<User> getAllUser() {
        return this.userMapper.getAllUser();
    }

    public User getById(String id) {
        return this.userMapper.getById(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteUser(String id) throws Exception {
        this.userMapper.deleteUser(id);
        throw new Exception();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateUser(Map<String, Object> map) {
        this.userMapper.updateUser(map);
//        throw new RuntimeException("aaaa");
    }
}