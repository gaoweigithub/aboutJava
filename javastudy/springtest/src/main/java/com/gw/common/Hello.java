package com.gw.common;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Hello {
    private String name;

    @Bean(name = "wahahah")
    private Hello getMyHello() {
        Hello hello = new Hello();
        hello.setName("gaowei");
        return hello;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
