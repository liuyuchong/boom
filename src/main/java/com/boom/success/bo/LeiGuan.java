package com.boom.success.bo;

import com.boom.success.consts.LeiGuanStatusEnums;
import lombok.Data;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Data
@Table("leiguan_info")
public class LeiGuan {
    @Id
    private Long id;

    //固定码
    @Column("fix_code")
    private String fixCode;

    //发码
    @Column("child_code")
    private Integer childCode;

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
     * @see LeiGuanStatusEnums
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
