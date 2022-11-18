package com.durker.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Date;

@Aspect
@Component
public class TimeHardlerAdvice {

    @Pointcut("execution(* com.durker.service.impl.*.*(..))")
    private void pointcut() {
    }

    @Before("execution(* com.durker.service.impl.*.insert(..)) || execution(* com.baomidou.mybatisplus.extension.service.IService.save(..))")
    public void insertCut(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Class<?> clazz = args[0].getClass();

        Method method = null;
        try {
            method = clazz.getMethod("setCreateTime", Date.class);
            method.invoke(args[0], new Date());

        } catch (Exception e) {

        }

        try {
            method = clazz.getMethod("setUpdateTime", Date.class);
            method.invoke(args[0], new Date());

        } catch (Exception e) {

        }

    }

    @Before("execution(* com.durker.service.impl.*.update(..)) || execution(* com.baomidou.mybatisplus.extension.service.IService.updateById(..))")
    public void updateCut(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Class<?> clazz = args[0].getClass();
        Method method = null;
        try {
            method = clazz.getMethod("setUpdateTime", Date.class);
            method.invoke(args[0], new Date());

        } catch (Exception e) {

        }
    }
}
