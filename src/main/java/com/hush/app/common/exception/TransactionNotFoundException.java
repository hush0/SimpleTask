package com.hush.app.common.exception;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TransactionNotFoundException extends RuntimeException {
    public TransactionNotFoundException(String message) {
        super(message);
    }
}
