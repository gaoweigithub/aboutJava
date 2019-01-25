package com.gw.container.common;

import com.gw.starter.BaseStarter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
