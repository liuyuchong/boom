package com.boom.success.controller;

import com.boom.success.consts.Result;
import com.boom.success.request.DetailResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DetailController {

    @RequestMapping(value = "/api/detail/query",method = RequestMethod.GET)
    public Result<DetailResponse> query(@RequestParam(required = false) Integer pageNo){
        return null;
    }
}
