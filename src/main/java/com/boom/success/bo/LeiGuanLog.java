package com.boom.success.bo;

import com.boom.success.consts.LeiGuanStatusEnums;
import lombok.Data;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Data
@Table("leiguan_log")
public class LeiGuanLog {
    @Id
    private Long id;

    @Column
    private Long date;

    /**
     * @see LeiGuanStatusEnums
     */
    @Column
    private String operation;

    //固定码
    @Column("fix_code")
    private String fixCode;

    //发码起始值
    @Column("child_from")
    private Integer from;

    //发码结束值
    @Column("child_to")
    private Integer to;

    //保管人
    @Column
    private String keeper;

    //领退人
    @Column
    private String consumer;

    //操作人
    @Column
    private String operator;

}
