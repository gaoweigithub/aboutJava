package com.gw.component.mybatis事务;

import com.gw.pojo.User1;
import com.gw.pojo.User2;
import com.gw.service.User1Service;
import com.gw.service.User2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
public class RequiredNewOuterMethodOpenTrasaction {
    @Autowired
    private User1Service user1Service;

    @Autowired
    private User2Service user2Service;

    /**
     * 张三回滚  李四王五不回滚
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void transaction_exception_required_requiresNew_requiresNew() {
        User1 user1 = new User1();
        user1.setName("张三");
        user1Service.addRequired(user1);

        User2 user2 = new User2();
        user2.setName("李四");
        user2Service.addRequiresNew(user2);

        User2 user3 = new User2();
        user3.setName("王五");
        user2Service.addRequiresNew(user3);
        throw new RuntimeException();
    }

    /**
     * 王五有异常回滚   王五的异常导致外层函数异常然后张三回滚  李四新开事务  不回滚
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void transaction_required_requiresNew_requiresNew_exception() {
        User1 user1 = new User1();
        user1.setName("张三");
        user1Service.addRequired(user1);

        User2 user2 = new User2();
        user2.setName("李四");
        user2Service.addRequiresNew(user2);

        User2 user3 = new User2();
        user3.setName("王五");
        user2Service.addRequiresNewException(user3);
    }

    /**
     * 由于王五的异常被捕捉  所以王五回滚  张三李四不回滚
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void transaction_required_requiresNew_requiresNew_exception_try() {
        User1 user1 = new User1();
        user1.setName("张三");
        user1Service.addRequired(user1);

        User2 user2 = new User2();
        user2.setName("李四");
        user2Service.addRequiresNew(user2);

        User2 user3 = new User2();
        user3.setName("王五");
        try {
            user2Service.addRequiresNewException(user3);
        } catch (Exception e) {
            System.out.println("回滚");
        }
    }
}
