package com.hush.app.common;

import com.hush.app.common.exception.IllegalParamsException;
import com.hush.app.common.exception.TransactionNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.util.MultiValueMap;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author: huangshuai
 * @Date: 6/13/25
 * @Version 1.0
 */
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleIllegalParam(IllegalParamsException ex) {
        return new ResponseEntity("param illegal: " + ex.getMessage(),HttpStatus.BAD_REQUEST );
    }


    @ExceptionHandler(TransactionNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleNotFound(TransactionNotFoundException ex) {
        return new ResponseEntity("transaction not found: " + ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleValidation(MethodArgumentNotValidException ex) {
        return new ResponseEntity("Validation error: " + ex.getMessage(),  HttpStatus.BAD_REQUEST);
    }

}
