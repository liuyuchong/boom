package com.boom.success.response.bo;

import lombok.Data;

@Data
public class LeiGuanBo {
    private Long id;
    private String fixCode;
    private String childCode;
    private Long storeTime;
    private Long sendTime;
    private Long backTime;
    private Long useTime;
    private Integer status;
    private String keeper;
    private String consumer;
}
