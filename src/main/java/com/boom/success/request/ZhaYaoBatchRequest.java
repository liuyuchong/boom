package com.boom.success.request;

import com.boom.success.consts.StandardsEnums;
import com.boom.success.consts.StatusEnums;
import lombok.Data;

@Data
public class ZhaYaoBatchRequest {
    //操作时间
    private Long date;
    //操作类型
    /**
     * @see StatusEnums
     */
    private Integer optType;
    //批次号
    private String batchNum;
    //箱号起始值
    private Integer boxFrom;
    //箱号结束值
    private Integer boxTo;
    //炸药规格
    /**
     * @see StandardsEnums
     */
    private Integer type;
    //柱号起始值
    private Integer colFrom;
    //柱号结束值
    private Integer colTo;
    //保管人
    private String keeper;
    //领退人
    private String consumer;

}
