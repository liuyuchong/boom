package com.boom.success.response.bo;

import com.boom.success.consts.StatusEnums;
import lombok.Data;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;

@Data
public class ZhaYaoLogBo {
    private Long id;
    private Long date;
    private String operation;
    private String batchNum;
    private String boxFrom;
    private String boxTo;
    private String colFrom;
    private String colTo;
    private String keeper;
    private String consumer;
    private String operator;

    //入库
    private Float store;
    //发出
    private Float send;
    //回退
    private Float back;
    //使用
    private Float consumed;
}