package com.boom.success.response;

import com.boom.success.bo.LeiGuanLog;
import lombok.Data;

import java.util.List;

@Data
public class LeiguanRecordResponse {
    private List<LeiGuanLog> records;
    //库存
    private int stock;
    private int total;
}
