package com.codestudy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {

    private Integer code;
    private String msg;
    private T data;

    public Result(Integer code, String msg) {
        this.code = 200;
        this.msg = msg;
    }

    public Integer idCode() {
        return code;
    }

    // 提供一个便捷的方法创建成功结果
    public static <T> Result<T> ok(String msg) {
        return new Result<>(200, msg);
    }

    // 提供一个便捷的方法创建成功结果并携带数据
    public static <T> Result<T> ok(String msg, T data) {
        return new Result<>(200, msg, data);
    }

    // 提供一个便捷的方法创建失败结果
    public static <T> Result<T> fail(String msg) {
        return new Result<>(400, msg);
    }

    // 提供一个便捷的方法创建失败结果并携带数据
    public static <T> Result<T> fail(String msg, T data) {
        return new Result<>(400, msg, data);
    }
}