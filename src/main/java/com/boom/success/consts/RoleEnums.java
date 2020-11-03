package com.boom.success.consts;

public enum RoleEnums {
    ADMIN(1,"系统管理员"),
    WORD(2,"读写权限"),
    GUEST(3,"只读权限");
    private int code;
    private String desc;

    RoleEnums(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public static RoleEnums getRole(Integer code) {
        if (code == null) {
            return null;
        }
        for (RoleEnums e : RoleEnums.values()) {
            if (code == e.code) {
                return e;
            }
        }
        return null;
    }
}
