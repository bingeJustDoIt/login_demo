package com.example.login_demo.aop;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import com.example.login_demo.util.SymmetricCryptoUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
@Slf4j
@Aspect
public class AesAspect {
    @Around(value = "@annotation(com.example.login_demo.aop.Aes)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // TODO: 在方法前或方法后增加您的操作
        Object result = null;  // 执行被增强的方法
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
//            throw new RuntimeException(e);
//            出异常也进行加密
            return SymmetricCryptoUtil.encrypt(e.getMessage());
        }
        // TODO: 在方法前或方法后增加您的操作
        return SymmetricCryptoUtil.encrypt(String.valueOf(result));
    }

}
