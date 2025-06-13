package com.hush.app.common.exception;

public class IllegalParamsException extends RuntimeException {

    public String paramName;

    public IllegalParamsException(String paramName, String message) {
        super(message);
        this.paramName = paramName;
    }

}
