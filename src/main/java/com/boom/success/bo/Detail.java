package com.boom.success.bo;

import lombok.Data;
import org.nutz.dao.entity.annotation.*;

@Data
@Table("boom_detail")
public class Detail {
    @Id
    private Long id;

    @Column
    private Long date;

    @Column("line_num")
    private String lineNum;

    @Column("stake_num")
    private String stakeNum;

    @Column
    private Float height;

    @Column("fix_code")
    private String fixCode;

    @Column("child_code")
    private Integer childCode;

    @Column("batch_num")
    private String batchNum;

    @Column("box_num")
    private Integer boxNum;

    @Column("col_num")
    private String colNum;

    @Column
    private Float count;

    @Column
    private String down;

    @Column
    private String packager;

    @Column
    private String mark;

    @ColDefine(type = ColType.MYSQL_JSON)
    @Column
    private Video videos;

}
