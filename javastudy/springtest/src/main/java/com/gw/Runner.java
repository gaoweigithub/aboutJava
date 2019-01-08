package com.gw;

import com.gw.common.Hello;
import com.gw.common.Student;
import com.gw.common.Welcome;
import com.gw.custome.ScanClass1;
import com.gw.pojo.User;
import com.gw.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;

public class Runner {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-main.xml");


        Hello hh = (Hello) context.getBean("getMyHello");
        System.out.println(hh.getName());

        Welcome welcome = (Welcome) context.getBean("welcomegw");
        welcome.sayWelcome();

        Student student = (Student) context.getBean(Student.class);
        System.out.println(student.getName());

        ScanClass1 scanClass1 = context.getBean(ScanClass1.class);
        scanClass1.print();

        UserService userService = context.getBean(UserService.class);
        int ii = userService.countAll();
        System.out.println(ii);

//        userService.insertUser(new User("100","gw"));

        Map<String,String> map = new HashMap<>(2);
        map.put("id","001");
        map.put("name","方傻逼");
        userService.update_insert(map,new User("101","wx"));
    }
}
