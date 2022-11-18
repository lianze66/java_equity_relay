package com.durker.common;

import lombok.Data;

@Data
public class Result {

    private String code;

    private String message;

    private Object data;

    private Result() {
    }

    public static Result ok() {
        Result result = new Result();
        result.setCode("200");
        result.setMessage("操作成功");
        result.setData(null);
        return result;
    }

    public static Result ok(Object data) {
        Result result = new Result();
        result.setCode("200");
        result.setMessage("操作成功");
        result.setData(data);
        return result;
    }

    public static Result fail(String desc) {
        Result result = new Result();
        result.setCode("000");
        result.setMessage(desc);
        result.setData(null);
        return result;
    }

}
