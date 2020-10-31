package com.boom.success.request;

import lombok.Data;

@Data
public class UrlUpdateReqeust {
    //耗材明细id
    private Long detailId;
    //更新视频链接类型 1警卫班视频 2交接发放视频 3包药视频 4下药视频 5废盲炮视频
    private Integer type;
    //url
    private String url;
}
