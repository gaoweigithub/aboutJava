package 事务测试;

import com.gw.component.mybatis事务.RequiredOuterMethodCloseTrasaction;
import com.gw.component.mybatis事务.RequiredOuterMethodOpenTrasaction;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 结论：以上试验结果我们证明在外围方法开启事务的情况下Propagation.REQUIRED修饰的内部方法会加入到外围方法的事务中，
 * 所有Propagation.REQUIRED修饰的内部方法和外围方法均属于同一事务，只要一个方法回滚，整个事务均回滚。
 */
public class TestRequiredOuterMethodOpenTrasaction {
    private ApplicationContext applicationContext;
    private RequiredOuterMethodCloseTrasaction requiredOuterMethodCloseTrasaction;
    private RequiredOuterMethodOpenTrasaction requiredOuterMethodOpenTrasaction;

    @Before
    public void init() {
        applicationContext = new ClassPathXmlApplicationContext("classpath:spring/spring-main.xml");
        requiredOuterMethodCloseTrasaction = applicationContext.getBean(RequiredOuterMethodCloseTrasaction.class);
        requiredOuterMethodOpenTrasaction = applicationContext.getBean(RequiredOuterMethodOpenTrasaction.class);
    }

    /**
     * “张三”、“李四”均未插入。	外围方法开启事务，内部方法加入外围方法事务，外围方法回滚，内部方法也要回滚。
     */
    @Test
    public void transaction_exception_required_required() {
        requiredOuterMethodOpenTrasaction.transaction_exception_required_required();
    }

    /**
     * “张三”、“李四”均未插入。	外围方法开启事务，内部方法加入外围方法事务，内部方法抛出异常回滚，外围方法感知异常致使整体事务回滚。
     */
    @Test
    public void transaction_required_required_exception() {
        requiredOuterMethodOpenTrasaction.transaction_required_required_exception();
    }

    /**
     * “张三”、“李四”均未插入。	外围方法开启事务，内部方法加入外围方法事务，内部方法抛出异常回滚，即使方法被catch不被外围方法感知，整个事务依然回滚。
     */
    @Test
    public void transaction_required_required_exception_try() {
        requiredOuterMethodOpenTrasaction.transaction_required_required_exception_try();
    }
}
