import com.gw.component.mybatis事务.RequiredNewOuterMethodOpenTrasaction;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 结论：在外围方法开启事务的情况下Propagation.REQUIRES_NEW修饰的内部方法依然会单独开启独立事务，
 * 且与外部方法事务也独立，内部方法之间、内部方法和外部方法事务均相互独立，互不干扰。
 */
public class TestRequiredNewOuterMethodOpenTrasaction {
    private ApplicationContext applicationContext;
    private RequiredNewOuterMethodOpenTrasaction requiredNewOuterMethodOpenTrasaction;

    @Before
    public void init() {
        applicationContext = new ClassPathXmlApplicationContext("classpath:spring/spring-main.xml");
        requiredNewOuterMethodOpenTrasaction = applicationContext.getBean(RequiredNewOuterMethodOpenTrasaction.class);
    }

    /**
     * “张三”未插入，“李四”插入，“王五”插入。	外围方法开启事务，插入“张三”方法和外围方法一个事务，插入“李四”方法、插入“王五”方法分别在独立的新建事务中，
     * 外围方法抛出异常只回滚和外围方法同一事务的方法，故插入“张三”的方法回滚。
     */
    @Test
    public void transaction_exception_required_requiresNew_requiresNew() {
        requiredNewOuterMethodOpenTrasaction.transaction_exception_required_requiresNew_requiresNew();
    }

    /**
     * “张三”未插入，“李四”插入，“王五”未插入。	外围方法开启事务，插入“张三”方法和外围方法一个事务，插入“李四”方法、插入“王五”方法分别在独立的新建事务中。
     * 插入“王五”方法抛出异常，首先插入 “王五”方法的事务被回滚，异常继续抛出被外围方法感知，外围方法事务亦被回滚，故插入“张三”方法也被回滚。
     */
    @Test
    public void transaction_required_requiresNew_requiresNew_exception() {
        requiredNewOuterMethodOpenTrasaction.transaction_required_requiresNew_requiresNew_exception();
    }

    /**
     * “张三”插入，“李四”插入，“王五”未插入。	外围方法开启事务，插入“张三”方法和外围方法一个事务，
     * 插入“李四”方法、插入“王五”方法分别在独立的新建事务中。插入“王五”方法抛出异常，
     * 首先插入“王五”方法的事务被回滚，异常被catch不会被外围方法感知，外围方法事务不回滚，故插入“张三”方法插入成功。
     */
    @Test
    public void transaction_required_requiresNew_requiresNew_exception_try() {
        requiredNewOuterMethodOpenTrasaction.transaction_required_requiresNew_requiresNew_exception_try();
    }
}
