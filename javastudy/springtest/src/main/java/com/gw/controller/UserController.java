package com.gw.controller;

import com.gw.annotations.NettyController;
import com.gw.annotations.NettyMapping;
import com.gw.mapper.User1Mapper;
import com.gw.pojo.User1;
import com.gw.pojo.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;

@NettyController("user")
public class UserController {
    @Autowired
    private User1Mapper user1Mapper;

    @NettyMapping("get")
    public User1 get(UserRequest request) {
        User1 rr = user1Mapper.selectByPrimaryKey(request.getId());
        return rr;
    }
}
