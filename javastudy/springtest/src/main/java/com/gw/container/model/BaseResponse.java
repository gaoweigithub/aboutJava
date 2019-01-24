package com.gw.container.model;

public class BaseResponse<T> {
    private boolean success;
    private String message;
    private T data;

    public BaseResponse(T data) {
        this.data = data;
    }
    public BaseResponse() {

    }
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
