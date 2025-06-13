package com.hush.app.common.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MethodArgumentNotValidException extends RuntimeException {

    public String method;

    public String arguements;

    public MethodArgumentNotValidException(String method, String arguements, String message) {
        super(message);
        this.method = method;
        this.arguements = arguements;
    }
}
