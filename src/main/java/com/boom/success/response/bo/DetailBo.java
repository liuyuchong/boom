package com.boom.success.response.bo;

import com.boom.success.bo.Video;
import lombok.Data;
import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;

@Data
public class DetailBo {
    private Long id;
    private Long date;
    private Integer lineNum;
    private String stakeNum;
    private Integer height;
    private String fixCode;
    private String childCode;
    private String batchNum;
    private String boxNum;
    private String colNum;
    private Float count;
    private String down;
    private String packager;
    private String mark;
    private Video videos;
}