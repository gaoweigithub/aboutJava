package com.gw.service;

import com.gw.model.po.User1;
public interface User1Service {
    void addRequired(User1 user);
//    void addRequiredException(User1 user);
    void addRequiresNew(User1 user1);
    void addNested(User1 user1);
}
