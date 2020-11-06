package com.boom.success.response;

import com.boom.success.response.bo.ZhaYaoBo;
import lombok.Data;

import java.util.List;

@Data
public class ZhaYaoResponse {
    private List<ZhaYaoBo> zhaYaoList;
    private int total;
}
