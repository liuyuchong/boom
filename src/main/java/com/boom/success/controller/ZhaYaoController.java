package com.boom.success.controller;

import com.boom.success.bo.ZhaYao;
import com.boom.success.bo.ZhaYaoLog;
import com.boom.success.consts.GeneralCode;
import com.boom.success.consts.Result;
import com.boom.success.consts.StandardsEnums;
import com.boom.success.consts.StatusEnums;
import com.boom.success.request.ZhaYaoBatchRequest;
import com.boom.success.response.ZhaYaoRecordResponse;
import com.boom.success.response.ZhaYaoResponse;
import com.boom.success.service.ZhaYaoService;
import com.boom.success.util.LoginUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;

@RestController
public class ZhaYaoController {

    @Autowired
    private ZhaYaoService service;

    /**
     * 查询符合条件的炸药信息
     */
    @RequestMapping(value = "/api/zhayao/list", method = RequestMethod.GET)
    public Result<ZhaYaoResponse> queryZhayaos(@RequestParam(required = false) String batchNum,
                                               @RequestParam(required = false) Integer boxFrom,
                                               @RequestParam(required = false) Integer boxTo,
                                               @RequestParam(required = false) Integer colFrom,
                                               @RequestParam(required = false) Integer colTo,
                                               @RequestParam(required = false) Integer status,
                                               @RequestParam(required = false) Integer pageNo,
                                               @RequestParam(required = false) Integer pageSize) {
        return Result.success(service.query(batchNum, boxFrom, boxTo, colFrom, colTo, status, pageNo, pageSize));
    }


    /**
     * 更新单个炸药信息 数据修复用 不计入操作日志
     */
    @RequestMapping(value = "/api/zhayao", method = RequestMethod.PUT)
    public Result<Boolean> update(@RequestBody ZhaYao zhaYao, HttpServletRequest servletRequest) {
        if (zhaYao.getId() == null) {
            return Result.fail(GeneralCode.Param_Error);
        }
        return Result.success(service.update(zhaYao));
    }

    /**
     * 批量插入/修改炸药信息
     */
    @RequestMapping(value = "/api/zhayao/batch", method = RequestMethod.POST)
    public Result batch(@RequestBody ZhaYaoBatchRequest request, HttpServletRequest servletRequest) {
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
        if (StringUtils.isEmpty(request.getBatchNum())) {
            return Result.fail(GeneralCode.Param_Error.getCode(), "请填写批次号");
        }
        if (request.getBoxFrom() == null || request.getBoxFrom() < 0) {
            return Result.fail(GeneralCode.Param_Error.getCode(), "请填写正确的箱号起始值");
        }
        if (request.getBoxTo() == null || request.getBoxTo() < 0) {
            return Result.fail(GeneralCode.Param_Error.getCode(), "请填写正确的箱号终止值");
        }
        if (request.getColFrom() != null && request.getColFrom() < 0) {
            return Result.fail(GeneralCode.Param_Error.getCode(), "请填写正确的柱号起始值");
        }
        if (request.getColTo() != null && request.getColTo() < 0) {
            return Result.fail(GeneralCode.Param_Error.getCode(), "请填写正确的柱号终止值");
        }
        if (request.getColFrom() != null && request.getColTo() != null && request.getColFrom() > request.getColTo()) {
            return Result.fail(GeneralCode.Param_Error.getCode(), "柱号终止值必须大于等于起始值");
        }
        if (request.getBoxTo() < request.getBoxFrom()) {
            return Result.fail(GeneralCode.Param_Error.getCode(), "箱号终止值必须大于等于起始值");
        }
        if (request.getOptType().intValue() == StatusEnums.INIT.getCode()) {
            if (StringUtils.isEmpty(request.getKeeper())) {
                return Result.fail(GeneralCode.Param_Error.getCode(), "入库必须填写保管人");
            }
            if (null == StandardsEnums.getByCode(request.getType())) {
                return Result.fail(GeneralCode.Param_Error.getCode(), "入库必须选择炸药规格");
            }
        }
        if (request.getOptType().intValue() != StatusEnums.INIT.getCode() && StringUtils.isEmpty(request.getConsumer())) {
            return Result.fail(GeneralCode.Param_Error.getCode(), "请填写领退人");
        }
        //操作炸药柱数
        int count = 0;
        //本次操作炸药总重量（kg）
        double totalWeight = 0;
        switch (statusEnums) {
            case INIT:
                //入库，检查批次下的箱号是否存在
                Result result = service.checkIfExist(request.getBatchNum(), request.getBoxFrom(), request.getBoxTo());
                if (result.getCode() != GeneralCode.SUCCESS.getCode()) {
                    return result;
                }
                //入库重量=箱数*规格
                totalWeight = (request.getBoxTo() - request.getBoxFrom()) * 24;
                count = service.batchInsert(request);
                break;
            default:
                //检查对应的批次号、箱号、柱号是否存在
                Result<Double> result2 = service.checkIfExist2(request.getBatchNum(), request.getBoxFrom(), request.getBoxTo(), request.getColFrom(), request.getColTo());
                if (result2.getCode() != GeneralCode.SUCCESS.getCode()) {
                    return result2;
                }
                totalWeight = result2.getData();
                count = service.batchUpdate(request);
        }

        //计入批量操作日志
        ZhaYaoLog log = new ZhaYaoLog();
        BeanUtils.copyProperties(request, log);
        log.setOperator(username);
        log.setOperation(statusEnums.getDesc());
        log.setCount((float) totalWeight);
        service.insertLog(log);
        return Result.success(count);
    }

    /**
     * 按日期查账单
     */
    @RequestMapping(value = "/api/zhayao/log", method = RequestMethod.GET)
    public Result<ZhaYaoRecordResponse> getLog(@RequestParam Long date,
                                               @RequestParam(required = false) Integer pageNo,
                                               @RequestParam(required = false) Integer paseSize) {
        return Result.success(service.getLog(date, pageNo, paseSize));
    }

}
