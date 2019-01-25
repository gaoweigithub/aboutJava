package com.gw.container.common;

import org.springframework.http.HttpMethod;

import java.lang.reflect.Method;

public class Action {

    private Method method;

    private Object object;

    private HttpMethod[] methods;

    public HttpMethod[] getMethods() {
        return methods;
    }

    public void setMethods(HttpMethod[] methods) {
        this.methods = methods;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }


}
