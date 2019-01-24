package com.gw.controller;

import com.alibaba.fastjson.JSONObject;
import com.gw.annotations.NettyController;
import com.gw.annotations.NettyMapping;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;

import java.util.HashMap;
import java.util.Map;


@NettyController(value = "netty")
public class TestNettyController {

    @NettyMapping(value = "get", METHODS = {HttpMethod.POST, HttpMethod.GET})
    public String get(String username) {
        JSONObject resultJson = new JSONObject();
        Map<String, String> loginResult = new HashMap<String, String>();
        loginResult.put("username", username);
        loginResult.put("age", "20");
        loginResult.put("sex", "boy");

        resultJson.put("code", 200);
        resultJson.put("msg", "登录成功");
        resultJson.put("result", loginResult);

        return JSONObject.toJSONString(resultJson);
    }
}