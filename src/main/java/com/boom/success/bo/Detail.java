package com.boom.success.bo;

import lombok.Data;
import org.nutz.dao.entity.annotation.*;

@Data
@Table("detail")
public class Detail {
    @Id
    private Long id;

    @Column
    private Long date;

    @Column("line_num")
    private Integer lineNum;

    @Column("stake_num")
    private String stakeNum;

    @Column
    private Integer height;

    @Column("fix_code")
    private String fixCode;

    @Column("child_code")
    private String childCode;

    @Column("batch_num")
    private String batchNum;

    @Column("box_num")
    private Integer boxNum;

    @Column("col_num")
    private Integer colNum;

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
