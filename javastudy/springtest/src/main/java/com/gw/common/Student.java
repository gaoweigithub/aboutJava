package com.gw.common;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Student {
    private String name;
    @PostConstruct
    public void Init(){
        name = "ggww";
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
