package com.boom.success.response.bo;

import lombok.Data;

@Data
public class ZhaYaoLogBo {
    private Long id;
    private Long date;
    private String operation;
    private String batchNum;
    private String box;
    private String col;
    private String keeper;
    private String consumer;
    private String operator;

    //入库
    private Float store;
    //发出
    private Float send;
    //回退
    private Float back;
}
