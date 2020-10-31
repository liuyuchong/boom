package com.boom.success.response;

import com.boom.success.bo.ZhaYaoLog;
import lombok.Data;

import java.util.List;

@Data
public class ZhaYaoRecordResponse {
    private List<ZhaYaoLog> records;
    //库存
    private int stock;
    private int total;
}
