package com.gw.controller;

import com.gw.annotations.NettyController;
import com.gw.annotations.NettyMapping;
import com.gw.service.DemoElService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;

import java.util.Map;

@NettyController("inject")
public class InjectTestController {

    @Autowired
    private DemoElService demoElService;

    @NettyMapping(value = "test", METHODS = {HttpMethod.GET, HttpMethod.POST})
    public Map<String, Object> test() {
        return demoElService.outputResource();
    }
}
