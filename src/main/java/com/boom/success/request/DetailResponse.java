package com.boom.success.request;

import com.boom.success.bo.Detail;
import com.boom.success.response.bo.DetailBo;
import lombok.Data;

import java.util.List;

@Data
public class DetailResponse {
    private int total;
    private List<DetailBo> detailList;
}
