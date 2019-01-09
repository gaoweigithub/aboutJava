package com.gw.service;

import com.gw.pojo.User1;
import com.gw.pojo.User2;
import org.springframework.stereotype.Service;
public interface User2Service {
    void addRequired(User2 user);
    void addRequiredException(User2 user);
    void addRequiresNew(User2 user2);
    void addRequiresNewException(User2 user2);
    void addNested(User2 user2);
    void addNestedException(User2 user2);
}
