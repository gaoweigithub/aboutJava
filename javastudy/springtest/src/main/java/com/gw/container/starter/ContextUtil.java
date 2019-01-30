package com.gw.container.starter;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@StarterOrderAnnotation(order = 1)
public class ContextUtil extends BaseStarter {
    private static ApplicationContext context;

    public static ApplicationContext getContext() {
        return context;
    }

    private static void setContext(ApplicationContext context) {
        ContextUtil.context = context;
    }

    @Override
    public void _process() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-main.xml");
        setContext(context);
    }
}
