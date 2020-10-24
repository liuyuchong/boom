package com.boom.success.bo;

import com.boom.success.consts.StatusEnums;
import lombok.Data;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("zhayao_log")
@Data
public class ZhaYaoLog {
    @Id
    private Long id;

    @Column
    private Long date;

    /**
     * @see StatusEnums
     */
    @Column
    private String operation;

    //批次号
    @Column("batch_num")
    private String batchNum;

    //箱号起始值
    @Column("box_from")
    private Integer boxFrom;

    //箱号结束值
    @Column("box_from")
    private Integer boxTo;

    //柱号起始值
    @Column("col_from")
    private Integer colFrom;

    //柱号结束值
    @Column("col_to")
    private Integer colTo;

    //操作数量(kg)
    @Column
    private Integer count;

    //保管人
    @Column
    private String keeper;

    //领退人
    @Column
    private String consumer;

    //操作人
    @Column
    private String operator;

    //入库
    private Integer store;
    //发出
    private Integer send;
    //回退
    private Integer back;
    //使用
    private Integer consumed;
}
