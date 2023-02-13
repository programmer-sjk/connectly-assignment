package com.product.common;

import org.springframework.http.HttpStatus;

public class ResponseMessage<T> {
    private int statusCode = HttpStatus.OK.value();
    private String message = "";
    protected T data;

    public static ResponseMessage<String> ok() {
        return new ResponseMessage<>("");
    }

    public static ResponseMessage<String> badRequest(String message) {
        return new ResponseMessage<>(HttpStatus.BAD_REQUEST.value(), message, "");
    }

    public ResponseMessage(T data) {
        this.data = data;
    }

    public ResponseMessage(int statusCode, String message, T data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
