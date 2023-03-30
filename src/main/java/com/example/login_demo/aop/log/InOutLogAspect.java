package com.example.login_demo.aop.log;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: uegmt-master
 * @description: 自定义切面
 * @author: HJB
 * @create: 2023-02-21 10:55
 **/
@Component
@Slf4j
@Aspect
public class InOutLogAspect {
    @Pointcut("@annotation(com.example.login_demo.aop.log.InOutLog)")
    public void myPointCut() {
    }


    @Around("@within(com.example.login_demo.aop.log.InOutLog)")
    public Object doAroundClass(ProceedingJoinPoint joinPoint) throws Throwable {
        InOutLog annotation = joinPoint.getTarget().getClass().getAnnotation(InOutLog.class);
        InOutLog methodAnnotation = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(InOutLog.class);
        Object result;
        if (methodAnnotation != null) {
            //以方法上的注解为准
            result = joinPoint.proceed();
        } else {
            //以类上的注解为准
            result = joinPoint.proceed();
        }
        logSaveOrNot(annotation);
        return result;
    }

    private void logSaveOrNot(InOutLog annotation) {
        if (annotation.logType().equals(InOutLogTypeEnum.SAVE)) {
            log.info("模拟入库操作");
        } else if (annotation.logType().equals(InOutLogTypeEnum.NO_SAVE)) {
            log.info("不入库");
        }
    }


    @Around("@annotation(com.example.login_demo.aop.log.InOutLog)")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        InOutLog annotation = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(InOutLog.class);
        String direction = null;
        String className = null;
        String methodName = null;
        try {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();

            className = joinPoint.getTarget().getClass().getName();
            methodName = joinPoint.getSignature().getName();
            Object[] parameters = joinPoint.getArgs();
            direction = annotation.direction().getMsg();
//            log.info("==================接口请求日志开始==================");
            log.info("接口方向:{} URL:{} IP:{} HTTP_METHOD:{} 类名.方法名:{}.{} 请求参数:{}", direction, request.getRequestURL().toString(), request.getRemoteAddr(),
                    request.getMethod(), className, methodName, JSON.toJSONString(parameters));
//            log.info("==================接口请求日志结束==================");
        } catch (Throwable e) {
            log.info("around " + joinPoint + " with exception : " + e.getMessage());
        }
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long time = System.currentTimeMillis() - startTime;
//        log.info("==================接口返回日志开始==================");
        log.info("接口方向:{} 类名.方法名:{}.{} 返回结果:{} 耗时:{} ms", direction, className, methodName, JSON.toJSONString(result), time);
//        log.info("==================接口返回日志结束==================");
        return result;
    }


    @AfterThrowing(pointcut = "myPointCut()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
        // 通过request获取登陆用户信息
        // HttpServletRequest request = ((ServletRequestAttributes)
        // RequestContextHolder.getRequestAttributes()).getRequest();
        try {
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            InOutLog annotation = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(InOutLog.class);
            String direction = annotation.direction().getMsg();
            Object[] parameters = joinPoint.getArgs();
//            log.info("==================接口异常日志开始==================");
            log.info("接口方向: {} 异常方法:{}.{} 参数:{} 异常信息:{}", direction, className, methodName, JSON.toJSONString(parameters), e.getMessage());
//            log.info("==================接口异常日志结束==================");
        } catch (Exception ex) {
            log.error("异常信息:{}", ex.getMessage());
            throw ex;
        }
    }
}
