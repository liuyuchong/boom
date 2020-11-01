package com.boom.success.response;

import com.boom.success.response.bo.LeiGuanBo;
import lombok.Data;

import java.util.List;

@Data
public class LeiGuanResponse {
    private List<LeiGuanBo> leiGuanList;
    private int total;
}
