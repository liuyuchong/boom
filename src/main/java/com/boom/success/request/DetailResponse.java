package com.boom.success.request;

import com.boom.success.bo.Detail;
import lombok.Data;

import java.util.List;

@Data
public class DetailResponse {
    private int total;
    private List<Detail> detailList;
}
