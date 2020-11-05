package com.boom.success.consts;

public enum StatusEnums {
    INIT(1,"入库"),
    ON_GOING(2,"发出"),
    BACK(3,"退回");
    private int code;
    private String desc;

    StatusEnums(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static StatusEnums get(Integer code) {
        if (code == null) {
            return null;
        }
        for (StatusEnums e : StatusEnums.values()) {
            if (e.getCode() == code) {
                return e;
            }
        }
        return null;
    }
}
