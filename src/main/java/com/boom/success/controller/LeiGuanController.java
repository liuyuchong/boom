package com.boom.success.controller;

import com.boom.success.bo.LeiGuan;
import com.boom.success.bo.LeiGuanLog;
import com.boom.success.consts.GeneralCode;
import com.boom.success.consts.Result;
import com.boom.success.consts.StatusEnums;
import com.boom.success.request.LeiGuanBatchRequest;
import com.boom.success.response.LeiGuanResponse;
import com.boom.success.response.LeiguanRecordResponse;
import com.boom.success.service.LeiGuanService;
import com.boom.success.util.LoginUtil;
import com.boom.success.util.NumberCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class LeiGuanController {

    @Autowired
    private LeiGuanService service;

    /**
     * 查询符合条件的雷管信息
     *
     * @param fixCode   固定码
     * @param childCode 发码
     * @param status    状态 @see com.boom.success.consts.StatusEnums
     * @param pageNo    页码
     * @param pageSize  页面大小
     * @return
     */
    @RequestMapping(value = "/api/leiguan/list", method = RequestMethod.GET)
    public Result<LeiGuanResponse> queryLeiguans(@RequestParam(required = false) String fixCode,
                                                 @RequestParam(required = false) Integer childCode,
                                                 @RequestParam(required = false) Integer from,
                                                 @RequestParam(required = false) Integer to,
                                                 @RequestParam(required = false) Integer status,
                                                 @RequestParam(required = false) String keeper,
                                                 @RequestParam(required = false) String consumer,
                                                 @RequestParam(required = false) Integer pageNo,
                                                 @RequestParam(required = false) Integer pageSize) {
        return Result.success(service.query(fixCode, childCode, from, to, status, keeper, consumer, pageNo, pageSize));
    }


    /**
     * 更新单个雷管信息 数据修复用 不计入操作日志
     */
    @RequestMapping(value = "/api/leiguan", method = RequestMethod.PUT)
    public Result<Boolean> update(@RequestBody LeiGuan leiGuan, HttpServletRequest servletRequest) {
        if (leiGuan.getId() == null) {
            return Result.fail(GeneralCode.Param_Error);
        }
        return Result.success(service.update(leiGuan));
    }

    /**
     * 批量插入/修改雷管信息
     */
    @RequestMapping(value = "/api/leiguan/batch", method = RequestMethod.POST)
    public Result<Integer> batch(@RequestBody LeiGuanBatchRequest request, HttpServletRequest servletRequest) {
        String username = LoginUtil.getUsername(servletRequest);
        if (username == null) {
            return Result.fail(GeneralCode.NOT_AUTHORIZED.getCode(), "请登录");
        }
        if (request.getDate() == null) {
            return Result.fail(GeneralCode.Param_Error.getCode(), "请选择操作日期");
        }
        StatusEnums statusEnums = StatusEnums.get(request.getOptType());
        if (statusEnums == null) {
            return Result.fail(GeneralCode.Param_Error.getCode(), "请选择操作类型");
        }
        if (StringUtils.isEmpty(request.getFixCode())) {
            return Result.fail(GeneralCode.Param_Error.getCode(), "请填写固定码");
        }
        if (request.getFrom1() != null && request.getFrom1() < 0) {
            return Result.fail(GeneralCode.Param_Error.getCode(), "请填写正确的雷管发码");
        }
        if (request.getFrom2() != null && request.getFrom2() < 0) {
            return Result.fail(GeneralCode.Param_Error.getCode(), "请填写正确的雷管发码");
        }
        if (request.getFrom3() != null && request.getFrom3() < 0) {
            return Result.fail(GeneralCode.Param_Error.getCode(), "请填写正确的雷管发码");
        }
        if (request.getTo1() != null && request.getTo1() < 0) {
            return Result.fail(GeneralCode.Param_Error.getCode(), "请填写正确的雷管发码");
        }
        if (request.getTo2() != null && request.getTo2() < 0) {
            return Result.fail(GeneralCode.Param_Error.getCode(), "请填写正确的雷管发码");
        }
        if (request.getTo3() != null && request.getTo3() < 0) {
            return Result.fail(GeneralCode.Param_Error.getCode(), "请填写正确的雷管发码");
        }
        if ((request.getFrom1() != null && request.getTo1() != null) && (request.getFrom1() > request.getTo1())) {
            return Result.fail(GeneralCode.Param_Error.getCode(), "终止值必须大于等于起始值");
        }
        if ((request.getFrom2() != null && request.getTo2() != null) && (request.getFrom2() > request.getTo2())) {
            return Result.fail(GeneralCode.Param_Error.getCode(), "终止值必须大于等于起始值");
        }
        if ((request.getFrom3() != null && request.getTo3() != null) && (request.getFrom3() > request.getTo3())) {
            return Result.fail(GeneralCode.Param_Error.getCode(), "终止值必须大于等于起始值");
        }
        if (request.getOptType().intValue() == StatusEnums.INIT.getCode() && StringUtils.isEmpty(request.getKeeper())) {
            return Result.fail(GeneralCode.Param_Error.getCode(), "入库必须填写保管人");
        }
        if (request.getOptType().intValue() != StatusEnums.INIT.getCode() && StringUtils.isEmpty(request.getConsumer())) {
            return Result.fail(GeneralCode.Param_Error.getCode(), "请填写领退人");
        }
        //交叉校验
        if (NumberCheck.in(request.getFrom1(), request.getFrom2(), request.getTo2())||NumberCheck.in(request.getFrom1(), request.getFrom3(), request.getTo3())) {
            return Result.fail(GeneralCode.Param_Error.getCode(), "数值不能交叉");
        }
        if (NumberCheck.in(request.getTo1(), request.getFrom2(), request.getTo2())||NumberCheck.in(request.getTo1(), request.getFrom3(), request.getTo3())) {
            return Result.fail(GeneralCode.Param_Error.getCode(), "数值不能交叉");
        }
        if (NumberCheck.in(request.getFrom2(), request.getFrom1(), request.getTo1())||NumberCheck.in(request.getFrom2(), request.getFrom3(), request.getTo3())) {
            return Result.fail(GeneralCode.Param_Error.getCode(), "数值不能交叉");
        }
        if (NumberCheck.in(request.getTo2(), request.getFrom1(), request.getTo1())||NumberCheck.in(request.getTo2(), request.getFrom3(), request.getTo3())) {
            return Result.fail(GeneralCode.Param_Error.getCode(), "数值不能交叉");
        }
        if (NumberCheck.in(request.getFrom3(), request.getFrom2(), request.getTo2())||NumberCheck.in(request.getFrom3(), request.getFrom1(), request.getTo1())) {
            return Result.fail(GeneralCode.Param_Error.getCode(), "数值不能交叉");
        }
        if (NumberCheck.in(request.getTo3(), request.getFrom2(), request.getTo2())||NumberCheck.in(request.getTo3(), request.getFrom1(), request.getTo1())) {
            return Result.fail(GeneralCode.Param_Error.getCode(), "数值不能交叉");
        }

        int count = 0;
        switch (statusEnums) {
            case INIT:
                //入库，检查固定码对应的发码是否已存在
                Result result = service.checkIfExist(request.getFixCode(), request.getFrom1(), request.getTo1());
                if (result.getCode() != GeneralCode.SUCCESS.getCode()) {
                    return result;
                }
                result = service.checkIfExist(request.getFixCode(), request.getFrom2(), request.getTo2());
                if (result.getCode() != GeneralCode.SUCCESS.getCode()) {
                    return result;
                }
                result = service.checkIfExist(request.getFixCode(), request.getFrom3(), request.getTo3());
                if (result.getCode() != GeneralCode.SUCCESS.getCode()) {
                    return result;
                }
                count = service.batchInsert(request,username);
                break;
            default:
                //检查对应的固定码 发码是否存在
                result = service.checkIfExist2(request.getFixCode(), request.getFrom1(), request.getTo1());
                if (result.getCode() != GeneralCode.SUCCESS.getCode()) {
                    return result;
                }
                result = service.checkIfExist2(request.getFixCode(), request.getFrom2(), request.getTo2());
                if (result.getCode() != GeneralCode.SUCCESS.getCode()) {
                    return result;
                }
                result = service.checkIfExist2(request.getFixCode(), request.getFrom2(), request.getTo2());
                if (result.getCode() != GeneralCode.SUCCESS.getCode()) {
                    return result;
                }
                count = service.batchUpdate(request,username);
        }

        return Result.success(count);
    }

    /**
     * 按日期查账单
     */
    @RequestMapping(value = "/api/leiguan/log", method = RequestMethod.GET)
    public Result<LeiguanRecordResponse> getLog(@RequestParam(required = false) Long startTime,
                                                @RequestParam(required = false) Long endTime,
                                                @RequestParam(required = false) String keeper,
                                                @RequestParam(required = false) String consumer,
                                                @RequestParam(required = false) Integer pageNo,
                                                @RequestParam(required = false) Integer paseSize) {
        return Result.success(service.getLog(startTime, endTime, keeper, consumer, pageNo, paseSize));
    }

}
