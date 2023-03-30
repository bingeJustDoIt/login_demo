package com.example.login_demo.advice;

import com.example.login_demo.Result;
import com.example.login_demo.aop.AesAspect;
import com.example.login_demo.util.SymmetricCryptoUtil;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

//@RestControllerAdvice
public class MyBeforeBodyAdvice implements ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        System.out.println("MyBeforeBodyAdvice...");
        //aes加密什么的
        System.out.println("MyBeforeBodyAdvice Result封装完成");
        try {
            System.out.println("尝试解密:" + SymmetricCryptoUtil.decrypt(String.valueOf(body)));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return Result.success(body);
    }
}
