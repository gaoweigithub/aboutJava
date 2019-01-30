package com.gw.mapper;

import com.gw.model.po.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface UserMapper {
    public int countAll();
    public void insertUser(User user);
    public List<User> getAllUser();
    public User getById(String id);
    public void deleteUser(String id);
    public void updateUser(Map<String,Object> map);
}
