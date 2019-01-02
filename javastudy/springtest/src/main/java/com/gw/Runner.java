package com.gw;

import com.gw.common.Hello;
import com.gw.common.Student;
import com.gw.common.Welcome;
import com.gw.custome.ScanClass1;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Runner {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-main.xml");


        Hello hh = (Hello) context.getBean("getMyHello");
        System.out.println(hh.getName());

        Welcome welcome = (Welcome) context.getBean("welcomegw");
        welcome.sayWelcome();

        Student student = (Student) context.getBean(Student.class);
        System.out.println(student.getName());

        ScanClass1 scanClass1 = context.getBean(ScanClass1.class);
        scanClass1.print();
    }
}
