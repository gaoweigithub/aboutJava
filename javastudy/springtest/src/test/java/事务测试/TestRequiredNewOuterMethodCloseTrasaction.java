package 事务测试;

import com.gw.component.mybatis事务.RequiredNewOuterMethodCloseTrasaction;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestRequiredNewOuterMethodCloseTrasaction {
    private ApplicationContext applicationContext;
    private RequiredNewOuterMethodCloseTrasaction requiredNewOuterMethodCloseTrasaction;

    @Before
    public void init() {
        applicationContext = new ClassPathXmlApplicationContext("classpath:spring/spring-main.xml");
        requiredNewOuterMethodCloseTrasaction = applicationContext.getBean(RequiredNewOuterMethodCloseTrasaction.class);
    }

    /**
     * 	“张三”插入，“李四”插入。	外围方法没有事务，插入“张三”、“李四”方法都在自己的事务中独立运行,外围方法抛出异常回滚不会影响内部方法。
     */
    @Test
    public void notransaction_exception_requiresNew_requiresNew() {
        requiredNewOuterMethodCloseTrasaction.notransaction_exception_requiresNew_requiresNew();
    }

    /**
     * “张三”插入，“李四”未插入	外围方法没有开启事务，插入“张三”方法和插入“李四”方法分别开启自己的事务，插入“李四”方法抛出异常回滚，其他事务不受影响。
     */
    @Test
    public void notransaction_requiresNew_requiresNew_exception() {
        requiredNewOuterMethodCloseTrasaction.notransaction_requiresNew_requiresNew_exception();
    }
}
