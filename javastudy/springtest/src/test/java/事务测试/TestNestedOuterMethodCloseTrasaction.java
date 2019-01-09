package 事务测试;

import com.gw.component.mybatis事务.NestedOuterMethodCloseTrasaction;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 结论：通过这两个方法我们证明了在外围方法未开启事务的情况下Propagation.NESTED和Propagation.REQUIRED作用相同，
 * 修饰的内部方法都会新开启自己的事务，且开启的事务相互独立，互不干扰。
 */
public class TestNestedOuterMethodCloseTrasaction {
    private ApplicationContext applicationContext;
    private NestedOuterMethodCloseTrasaction nestedOuterMethodCloseTrasaction;

    @Before
    public void init() {
        applicationContext = new ClassPathXmlApplicationContext("classpath:spring/spring-main.xml");
        nestedOuterMethodCloseTrasaction = applicationContext.getBean(NestedOuterMethodCloseTrasaction.class);
    }

    /**
     * “张三”、“李四”均插入。	外围方法未开启事务，插入“张三”、“李四”方法在自己的事务中独立运行，
     * 外围方法异常不影响内部插入“张三”、“李四”方法独立的事务。
     */
    @Test
    public void notransaction_exception_nested_nested() {
        nestedOuterMethodCloseTrasaction.notransaction_exception_nested_nested();
    }

    /**
     * 张三”插入，“李四”未插入。	外围方法没有事务，
     * 插入“张三”、“李四”方法都在自己的事务中独立运行,所以插入“李四”方法抛出异常只会回滚插入“李四”方法，插入“张三”方法不受影响。
     */
    @Test
    public void notransaction_nested_nested_exception() {
        nestedOuterMethodCloseTrasaction.notransaction_nested_nested_exception();
    }
}
