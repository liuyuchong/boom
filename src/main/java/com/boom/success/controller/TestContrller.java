package com.boom.success.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestContrller {


    @RequestMapping("/test")
    public String test(){
        return "success";
    }
}
