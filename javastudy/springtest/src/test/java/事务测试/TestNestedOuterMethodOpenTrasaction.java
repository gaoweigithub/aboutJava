package 事务测试;

import com.gw.component.mybatis事务.NestedOuterMethodOpenTrasaction;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

/**
 * 结论：以上试验结果我们证明在外围方法开启事务的情况下Propagation.NESTED修饰的内部方法属于外部事务的子事务，
 * 外围主事务回滚，子事务一定回滚，而内部子事务可以单独回滚而不影响外围主事务和其他子事务
 */
public class TestNestedOuterMethodOpenTrasaction {
    private ApplicationContext applicationContext;
    private NestedOuterMethodOpenTrasaction nestedOuterMethodOpenTrasaction;

    @Before
    public void init() {
        applicationContext = new ClassPathXmlApplicationContext("classpath:spring/spring-main.xml");
        nestedOuterMethodOpenTrasaction = applicationContext.getBean(NestedOuterMethodOpenTrasaction.class);
    }

    /**
     * 预期都会回滚
     * 	“张三”、“李四”均未插入。	外围方法开启事务，内部事务为外围事务的子事务，外围方法回滚，内部方法也要回滚。
     */
    @Transactional
    @Test
    public void transaction_exception_nested_nested(){
        nestedOuterMethodOpenTrasaction.transaction_exception_nested_nested();
    }

    /**
     *  预期: 张三李四都回滚   李四的内部异常回滚  外部方法获感知到异常然后子事务都回滚
     *  “张三”、“李四”均未插入。	外围方法开启事务，内部事务为外围事务的子事务，内部方法抛出异常回滚，且外围方法感知异常致使整体事务回滚。
     */
    @Transactional
    @Test
    public void transaction_nested_nested_exception(){
        nestedOuterMethodOpenTrasaction.transaction_nested_nested_exception();
    }

    /**
     * 李四回滚  张三不回滚  子事务不影响外层事务
     * 张三”插入、“李四”未插入。	外围方法开启事务，内部事务为外围事务的子事务，插入“张三”内部方法抛出异常，可以单独对子事务回滚。
     */
    @Transactional
    @Test
    public void transaction_nested_nested_exception_try(){
        nestedOuterMethodOpenTrasaction.transaction_nested_nested_exception_try();
    }
}
