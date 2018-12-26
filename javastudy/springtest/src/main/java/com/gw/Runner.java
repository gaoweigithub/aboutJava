package com.gw;

import com.gw.common.Hello;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Runner {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-main.xml");
        Hello hh = (Hello) context.getBean("wahahah");
        System.out.println(hh.getName());
    }
}
