package com.boom.success.response;

import com.boom.success.bo.ZhaYao;
import lombok.Data;

import java.util.List;

@Data
public class ZhaYaoResponse {
    private List<ZhaYao> zhaYaoList;
    private int total;
}
