package com.boom.success.request;

import com.boom.success.consts.StatusEnums;
import lombok.Data;

@Data
public class LeiGuanBatchRequest {
    //操作时间
    private Long date;

    //操作类型
    /**
     * @see StatusEnums
     */
    private Integer optType;

    //固定码
    private String fixCode;

    //发码1起始值(含)
    private Integer from1;

    //发码1终止值(含)
    private Integer to1;

    //发码2起始值(含)
    private Integer from2;

    //发码2终止值(含)
    private Integer to2;

    //发码3起始值(含)
    private Integer from3;

    //发码3终止值(含)
    private Integer to3;

    //保管人
    private String keeper;

    //领退人
    private String consumer;
}
