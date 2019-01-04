package com.gw.service;

import com.gw.pojo.User;

import java.util.Map;

public interface UserService {
    public int countAll();
    public void insertUser(User user);
    public void update_insert(Map map, User user) throws Exception;
}
