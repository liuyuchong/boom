package com.boom.success.bo;

import com.boom.success.consts.StatusEnums;
import lombok.Data;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Data
@Table("zhayao_info")
public class ZhaYao {
    @Id
    private Long id;

    //批次号
    @Column("batch_num")
    private String batchNum;

    //箱号
    @Column("box_num")
    private Integer boxNum;

    //柱号
    @Column("col_num")
    private Integer colNum;

    //规格/单位(kg)
    @Column
    private float unit;

    //入库时间
    @Column("store_time")
    private Long storeTime;

    //发出时间
    @Column("send_time")
    private Long sendTime;

    //退回时间
    @Column("back_time")
    private Long backTime;

    //使用时间
    @Column("use_time")
    private Long useTime;

    /**
     * @see StatusEnums
     */
    //当前状态
    @Column
    private Integer status;

    //保管人
    @Column
    private String keeper;

    //领退人
    @Column
    private String consumer;
}
