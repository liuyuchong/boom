package com.boom.success.response.bo;

import lombok.Data;

@Data
public class LeiGuanLogBo {
    private Long id;
    private Long date;
    private String operation;
    private String fixCode;
    private String from;
    private String to;
    private String keeper;
    private String consumer;
    private String operator;
    //入库
    private Integer store;
    //发出
    private Integer send;
    //回退
    private Integer back;
}
