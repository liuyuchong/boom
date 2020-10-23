package com.boom.success.response;

import com.boom.success.bo.LeiGuan;
import lombok.Data;

import java.util.List;

@Data
public class LeiGuanResponse {
    private List<LeiGuan> leiGuanList;
    private int total;
}
