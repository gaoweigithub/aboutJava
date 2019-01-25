package com.gw.controller;

import com.gw.annotations.NettyController;
import com.gw.annotations.NettyMapping;
import com.gw.container.model.Header;
import com.gw.container.model.request.TestRequest;
import com.gw.container.model.response.TestResponse;
import org.springframework.http.HttpMethod;


@NettyController(value = "netty")
public class TestNettyController {

    @NettyMapping(value = "get", METHODS = {HttpMethod.POST, HttpMethod.GET})
    public TestResponse get(Header header111,TestRequest request) {

        StringBuffer sb = new StringBuffer();
        sb.append(header111 == null ? "" : "client:" + header111.getClientType() + "  ");
        sb.append(header111 == null ? "" : "deviceid:" + header111.getDeviceId() + "  ");
        if (request != null) {
            if (request.getId() != null) {
                sb.append("id:").append(request.getId()).append("   ");
            }
            if (request.getName() != null) {
                sb.append("name:").append(request.getName()).append("   ");
            }
        }
        TestResponse tr = new TestResponse();
        tr.setCode(1);
        tr.setWelcomeMsg(sb.toString());
        return tr;
    }
}