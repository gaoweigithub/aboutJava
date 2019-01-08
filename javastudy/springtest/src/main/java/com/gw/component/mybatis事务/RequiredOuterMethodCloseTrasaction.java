package com.gw.component.mybatis事务;

import com.gw.pojo.User1;
import com.gw.pojo.User2;
import com.gw.service.User1Service;
import com.gw.service.User2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RequiredOuterMethodCloseTrasaction {
    @Autowired
    private User1Service user1Service;

    @Autowired
    private User2Service user2Service;

    /**
     * 外围方法未开启事务 : “张三”、“李四”均插入。	外围方法未开启事务，插入“张三”、“李四”方法在自己的事务中独立运行，外围方法异常不影响内部插入“张三”、“李四”方法独立的事务。
     */
    public void notransaction_exception_required_required() {
        User1 user1 = new User1();
        user1.setName("张三");
        user1Service.addRequired(user1);

        User2 user2 = new User2();
        user2.setName("李四");
        user2Service.addRequired(user2);

        throw new RuntimeException();
    }

    /**
     * 外围方法没有事务 : “张三”插入，“李四”未插入。	外围方法没有事务，插入“张三”、“李四”方法都在自己的事务中独立运行,所以插入“李四”方法抛出异常只会回滚插入“李四”方法，插入“张三”方法不受影响。
     */
    public void notransaction_required_required_exception() {
        User1 user1 = new User1();
        user1.setName("张三");
        user1Service.addRequired(user1);

        User2 user2 = new User2();
        user2.setName("李四");
        user2Service.addRequiredException(user2);
    }
}
