package com.xuwenxing.securityDemo.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.HandlerMethod;

/**
 *
 * 统一的异常处理  可以作为日志管理 也可以利用注解来完善异常消息
 * Created by xuwx on 2018/10/19.
 */
@ControllerAdvice
public class ErrorControllerAdvice{

    @ExceptionHandler(Exception.class)
    public void handlerException(Exception e, HandlerMethod handlerMethod ){
        System.out.println("这就是统一的异常处理");
        System.out.println(e.getLocalizedMessage());
        System.out.println(handlerMethod.getBean().getClass());
        System.out.println(handlerMethod.getMethod().getName());
    }
}
