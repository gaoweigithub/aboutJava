import com.gw.component.mybatis事务.RequiredOuterMethodCloseTrasaction;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * https://segmentfault.com/a/1190000013341344#articleHeader5
 */
public class TestRequiredOuterMethodCloseTrasaction {
    private ApplicationContext applicationContext;
    private RequiredOuterMethodCloseTrasaction requiredOuterMethodCloseTrasaction;

    @Before
    public void init() {
        applicationContext = new ClassPathXmlApplicationContext("classpath:spring/spring-main.xml");
        requiredOuterMethodCloseTrasaction = (RequiredOuterMethodCloseTrasaction) applicationContext.getBean(RequiredOuterMethodCloseTrasaction.class);
    }

    /**
     * 外围方法未开启事务 : “张三”、“李四”均插入。	外围方法未开启事务，插入“张三”、“李四”方法在自己的事务中独立运行，外围方法异常不影响内部插入“张三”、“李四”方法独立的事务。
     */
    @Test
    public void notransaction_exception_required_required() {
        requiredOuterMethodCloseTrasaction.notransaction_exception_required_required();
    }

    /**
     * 外围方法没有事务 : “张三”插入，“李四”未插入。	外围方法没有事务，插入“张三”、“李四”方法都在自己的事务中独立运行,所以插入“李四”方法抛出异常只会回滚插入“李四”方法，插入“张三”方法不受影响。
     */
    @Test
    public void notransaction_required_required_exception() {
        requiredOuterMethodCloseTrasaction.notransaction_required_required_exception();
    }
}
