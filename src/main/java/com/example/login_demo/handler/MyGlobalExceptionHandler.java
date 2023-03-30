package com.example.login_demo.handler;

import com.example.login_demo.exception.MyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MyGlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Object handler(Exception exception) {
        System.out.println("MyGlobalExceptionHandler: "+exception.getMessage());
        return "出现异常";
    }
}
