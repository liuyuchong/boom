package com.boom.success.response.bo;

import lombok.Data;

@Data
public class ZhaYaoBo {
    private Long id;
    private String batchNum;
    private String boxNum;
    private String colNum;
    private float unit;
    private Long storeTime;
    private Long sendTime;
    private Long backTime;
    private Integer status;
    private String keeper;
    private String consumer;
}
