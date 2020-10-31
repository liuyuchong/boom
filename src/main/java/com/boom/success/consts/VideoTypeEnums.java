package com.boom.success.consts;

public enum VideoTypeEnums {
    GUARD(1, "警卫班视频"),
    SEND(2, "交接发放视频"),
    PACKAGE(3, "包药视频"),
    CONSUME(4, "下药视频"),
    BAD(5, "废盲炮视频");
    private int code;
    private String desc;

    VideoTypeEnums(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public static VideoTypeEnums get(Integer code) {
        if (code == null) {
            return null;
        }
        for (VideoTypeEnums e : VideoTypeEnums.values()) {
            if (code == e.getCode()) {
                return e;
            }
        }
        return null;
    }

}
