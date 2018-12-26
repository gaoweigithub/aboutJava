package com.gw.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.Resource;

public class Welcome {
//    @Resource(name = "getMyHello")
    @Autowired
    @Qualifier("getMyHello")
    private Hello hello;
    public void sayWelcome(){
        System.out.println("welcome " + hello.getName());
    }
}
