package com.gw;

import com.gw.common.Hello;
import com.gw.common.Student;
import com.gw.common.Welcome;
import com.gw.custome.ScanClass1;
import com.gw.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Runner {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-main.xml");


        Hello hh = (Hello) context.getBean("getMyHello");
        System.out.println(hh.getName());

        Welcome welcome = (Welcome) context.getBean("welcomegw");
        welcome.sayWelcome();

        Student student = context.getBean(Student.class);
        System.out.println(student.getName());

        ScanClass1 scanClass1 = context.getBean(ScanClass1.class);
        scanClass1.print();

        UserService userService = context.getBean(UserService.class);
        int ii = userService.countAll();
        System.out.println(ii);



    }
}
