package com.durker.config;

import com.durker.common.NoLoginException;
import com.durker.common.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

@RestControllerAdvice(basePackages = "com.durker.controller")
public class HandlerException {

    @ExceptionHandler
    public Result handleException(Exception e) {
        e.printStackTrace(); // 将出错信息打印到控制台。

        if (e instanceof ArithmeticException) {
            return Result.fail("发生除数不能为零的异常！");
        }
        if (e instanceof SQLException) {
            return Result.fail("发生数据库发常！");
        }
        if (e instanceof ClassCastException) {
            return Result.fail("发生类转型异常！");
        }
        if (e instanceof NullPointerException) {
            return Result.fail("发生空指针异常！");
        }
        if (e instanceof NoLoginException) {
            return Result.fail("访问此方法需要登录！");
        }
        return Result.fail("发生异常！");

    }
}
