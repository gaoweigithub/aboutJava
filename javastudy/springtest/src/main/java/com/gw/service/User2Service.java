package com.gw.service;

import com.gw.model.po.User2;

public interface User2Service {
    void addRequired(User2 user);
    void addRequiredException(User2 user);
    void addRequiresNew(User2 user2);
    void addRequiresNewException(User2 user2);
    void addNested(User2 user2);
    void addNestedException(User2 user2);
}
