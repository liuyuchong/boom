package com.boom.success.controller;

import com.boom.success.consts.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestContrller {


    @RequestMapping("/test")
    public Result<String> test(){
        return Result.success("success");
    }
}
