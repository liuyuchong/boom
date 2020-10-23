package com.boom.success.request;

import com.boom.success.consts.LeiGuanStatusEnums;
import lombok.Data;

@Data
public class LeiGuanBatchRequest {
    //操作时间
    private Long date;

    //操作类型
    /**
     * @see LeiGuanStatusEnums
     */
    private Integer optType;

    //固定码
    private String fixCode;

    //发码起始值(含)
    private Integer from;

    //发码终止值(含)
    private Integer to;

    //保管人
    private String keeper;

    //领退人
    private String consumer;
}
