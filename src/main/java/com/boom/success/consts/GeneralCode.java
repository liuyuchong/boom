package com.boom.success.consts;

public enum GeneralCode {
    Param_Error(400,"params error"),
    NOT_AUTHORIZED(401,"not authorized"),
    SUCCESS(0,"success");
    private int code;
    private String msg;

    GeneralCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
