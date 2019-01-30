package com.gw.component.mybatis事务;

import com.gw.model.po.User1;
import com.gw.model.po.User2;
import com.gw.service.User1Service;
import com.gw.service.User2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RequiredNewOuterMethodCloseTrasaction {
    @Autowired
    private User1Service user1Service;

    @Autowired
    private User2Service user2Service;

    public void notransaction_exception_requiresNew_requiresNew(){
        User1 user1=new User1();
        user1.setName("张三");
        user1Service.addRequiresNew(user1);

        User2 user2=new User2();
        user2.setName("李四");
        user2Service.addRequiresNew(user2);
        throw new RuntimeException();

    }
    public void notransaction_requiresNew_requiresNew_exception(){
        User1 user1=new User1();
        user1.setName("张三");
        user1Service.addRequiresNew(user1);

        User2 user2=new User2();
        user2.setName("李四");
        user2Service.addRequiresNewException(user2);
    }
}
