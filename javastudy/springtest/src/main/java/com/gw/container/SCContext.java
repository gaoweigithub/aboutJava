package com.gw.container;

import com.alibaba.fastjson.JSONObject;
import com.gw.container.model.BaseRequest;
import com.gw.container.model.BaseResponse;
import com.gw.container.model.Header;

import java.util.HashMap;
import java.util.Map;

public class SCContext {
    private String sourceUrl;
    private String serviceName;
    private String actionName;
    private Map<Object,Object> contextLocal = new HashMap<>(8);
    private String requestData;
    private Map<String,String> param = new HashMap<>();
    private BaseRequest request;
    private BaseResponse response;
    private JSONObject requestObj;
    private JSONObject responseObj;
    private boolean errorToExit;
    private String message;
    private Header header;

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public boolean isErrorToExit() {
        return errorToExit;
    }

    public void setErrorToExit(boolean errorToExit) {
        this.errorToExit = errorToExit;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setContextLocal(Map<Object, Object> contextLocal) {
        this.contextLocal = contextLocal;
    }

    public void setParam(Map<String, String> param) {
        this.param = param;
    }

    public BaseRequest getRequest() {
        return request;
    }

    public void setRequest(BaseRequest request) {
        this.request = request;
    }

    public BaseResponse getResponse() {
        return response;
    }

    public void setResponse(BaseResponse response) {
        this.response = response;
    }

    public JSONObject getRequestObj() {
        return requestObj;
    }

    public void setRequestObj(JSONObject requestObj) {
        this.requestObj = requestObj;
    }

    public JSONObject getResponseObj() {
        return responseObj;
    }

    public void setResponseObj(JSONObject responseObj) {
        this.responseObj = responseObj;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public Map<Object, Object> getContextLocal() {
        return contextLocal;
    }

    public String getRequestData() {
        return requestData;
    }

    public void setRequestData(String requestData) {
        this.requestData = requestData;
    }

    public Map<String, String> getParam() {
        return param;
    }
}
