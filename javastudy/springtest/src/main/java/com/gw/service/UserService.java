package com.gw.service;

import com.gw.model.po.User;

import java.util.Map;

public interface UserService {
    public int countAll();
    public void insertUser(User user);
    public void update_insert(Map map, User user) throws Exception;
    public void update(Map map);
    public void delete(String id) throws Exception;
}
