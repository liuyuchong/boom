package com.boom.success.consts;

public enum StandardsEnums {
    HALF(0, 0.5f, "0.5kg/柱"),
    ONE(1, 1, "1kg/柱"),
    TWO(2, 2, "2kg/柱");
    private int code;
    private float unit;
    private String desc;

    StandardsEnums(int code, float unit, String desc) {
        this.code = code;
        this.unit = unit;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public float getUnit() {
        return unit;
    }

    public String getDesc() {
        return desc;
    }

    public static StandardsEnums getByCode(Integer code) {
        if (code == null) {
            return null;
        }
        for (StandardsEnums e : StandardsEnums.values()) {
            if (e.getCode() == code) {
                return e;
            }
        }
        return null;
    }
}
