package com.gw.dao.inter;

import com.gw.model.po.User;

import java.util.List;
import java.util.Map;

public interface UserDao {
    public int countAll();
    public void insertUser(User user);
    public List<User> getAllUser();
    public User getById(String id);
    public void deleteUser(String id) throws Exception;
    public void updateUser(Map<String,Object> map);
}