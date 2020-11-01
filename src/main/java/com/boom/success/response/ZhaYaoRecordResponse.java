package com.boom.success.response;

import com.boom.success.bo.ZhaYaoLog;
import com.boom.success.response.bo.ZhaYaoLogBo;
import lombok.Data;

import java.util.List;

@Data
public class ZhaYaoRecordResponse {
    private List<ZhaYaoLogBo> records;
    //库存
    private int stock;
    private int total;
}
