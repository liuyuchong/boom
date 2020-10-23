package com.boom.success.consts;

public enum LeiGuanStatusEnums {
    INIT(1,"入库"),
    ON_GOING(2,"发出"),
    BACK(3,"退回"),
    CONSUMED(4,"已使用");
    private int code;
    private String desc;

    LeiGuanStatusEnums(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static LeiGuanStatusEnums get(Integer code) {
        if (code == null) {
            return null;
        }
        for (LeiGuanStatusEnums e : LeiGuanStatusEnums.values()) {
            if (e.getCode() == code) {
                return e;
            }
        }
        return null;
    }
}
