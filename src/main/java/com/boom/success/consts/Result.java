package com.boom.success.consts;

import lombok.Data;

@Data
public class Result<T> {
    private T data;
    private int code;
    private String msg;

    public Result(T data, int code, String msg) {
        this.data = data;
        this.code = code;
        this.msg = msg;
    }

    public static <T> Result<T> success(T data) {
        return new Result(data, 0, "success");
    }

    public static <T> Result<T> fail(int code, String msg) {
        return new Result(null, code, msg);
    }

    public static <T> Result<T> fail(GeneralCode code) {
        return new Result(null, code.getCode(), code.getMsg());
    }
}
