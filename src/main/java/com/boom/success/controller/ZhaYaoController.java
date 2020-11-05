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
import com.boom.success.util.NumberCheck;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
                                               @RequestParam(required = false) String keeper,
                                               @RequestParam(required = false) String consumer,
                                               @RequestParam(required = false) Integer pageNo,
                                               @RequestParam(required = false) Integer pageSize) {
        return Result.success(service.query(batchNum, boxFrom, boxTo, colFrom, colTo, status, keeper, consumer, pageNo, pageSize));
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
        //入库操作
        if (statusEnums.INIT.getCode() == request.getOptType()) {
            return init(request, username);
        }

        //其他操作
        if (StringUtils.isEmpty(request.getConsumer())) {
            return Result.fail(GeneralCode.Param_Error.getCode(), "请填写领退人");
        }

        //统计整箱箱号
        Set<Integer> boxes = new HashSet<>();
        if (!StringUtils.isEmpty(request.getBox())) {
            String spliter = request.getBox().contains(",") ? "," : "，";
            String[] boxStr = request.getBox().split(spliter);
            try {
                for (String e : boxStr) {
                    if (!StringUtils.isEmpty(e.trim())) {
                        boxes.add(Integer.valueOf(e));
                    }
                }
            } catch (Exception e) {
                return Result.fail(GeneralCode.Param_Error.getCode(), "请填写正确的箱号，多个箱号请以逗号隔开，如：1,4,9,8");
            }
        }
        if (!CollectionUtils.isEmpty(boxes)) {
            //检查箱子是否存在
            for (Integer boxNum : boxes) {
                if (null == service.queryByBox(request.getBatchNum(), boxNum)) {
                    return Result.fail(GeneralCode.Param_Error.getCode(), "炸药箱号不存在："+request.getBatchNum()+"->"+boxNum);
                }
            }
        }

        int count = 0;
        if (request.getSbox() != null) {
            if (boxes.contains(request.getSbox())) {
                return Result.fail(GeneralCode.Param_Error.getCode(), "同一箱号请勿同时填写整箱和散装，箱号："+request.getSbox());
            }
            if (request.getColFrom() == null && request.getColTo() == null) {
                return Result.fail(GeneralCode.Param_Error.getCode(), "请填写柱号");
            }
            if (request.getColFrom() == null) {
                request.setColFrom(request.getColTo());
            }
            if (request.getColTo() == null) {
                request.setColTo(request.getColFrom());
            }
            if (request.getColFrom() < 0) {
                return Result.fail(GeneralCode.Param_Error.getCode(), "请填写正确的柱号起始值");
            }
            if (request.getColTo() < 0) {
                return Result.fail(GeneralCode.Param_Error.getCode(), "请填写正确的柱号终止值");
            }
            if (request.getColFrom() > request.getColTo()) {
                return Result.fail(GeneralCode.Param_Error.getCode(), "柱号终止值必须大于等于起始值");
            }
            //检查箱号 柱号是否存在
            Result<Double> result2 = service.checkIfExist2(request.getBatchNum(), request.getSbox(), request.getSbox(), request.getColFrom(), request.getColTo());
            if (result2.getCode() != GeneralCode.SUCCESS.getCode()) {
                return Result.fail(GeneralCode.Param_Error.getCode(), "炸药不存在！ 箱号："+request.getSbox()+", 柱号："+request.getColFrom()+"-"+request.getColTo());
            }

            //散装炸药修改
            count += service.batchUpdate(request.getDate(), request.getOptType(), request.getBatchNum(), request.getSbox(), request.getColFrom(), request.getColTo(), request.getConsumer());
            ZhaYaoLog log = new ZhaYaoLog();
            log.setDate(request.getDate());
            log.setOperator(username);
            log.setOperation(statusEnums.getDesc());
            log.setBatchNum(request.getBatchNum());
            log.setBox(request.getSbox()+"");
            log.setCol(request.getColFrom()+"-"+request.getColTo());
            log.setCount(result2.getData().floatValue());
            log.setConsumer(request.getConsumer());
            service.insertLog(log);
        }

        //整箱批量修改
        if (!CollectionUtils.isEmpty(boxes)) {
            //检查箱子是否存在
            for (Integer boxNum : boxes) {
                count += service.batchUpdate(request.getDate(), request.getOptType(), request.getBatchNum(), boxNum, null, null, request.getConsumer());
                ZhaYaoLog log = new ZhaYaoLog();
                log.setDate(request.getDate());
                log.setOperator(username);
                log.setOperation(statusEnums.getDesc());
                log.setBatchNum(request.getBatchNum());
                log.setBox(boxNum+"");
                log.setCount((float) 24.0);
                log.setConsumer(request.getConsumer());
                service.insertLog(log);
            }
        }
        return Result.success(count);
    }

    private Result init(ZhaYaoBatchRequest request, String username) {
        if (StringUtils.isEmpty(request.getKeeper())) {
            return Result.fail(GeneralCode.Param_Error.getCode(), "请填写保管人");
        }
        if (request.getBoxFrom1() != null && request.getBoxFrom1() < 0) {
            return Result.fail(GeneralCode.Param_Error.getCode(), "请填写正确的炸药箱号");
        }
        if (request.getBoxFrom2() != null && request.getBoxFrom2() < 0) {
            return Result.fail(GeneralCode.Param_Error.getCode(), "请填写正确的炸药箱号");
        }
        if (request.getBoxFrom3() != null && request.getBoxFrom3() < 0) {
            return Result.fail(GeneralCode.Param_Error.getCode(), "请填写正确的炸药箱号");
        }
        if (request.getBoxTo1() != null && request.getBoxTo1() < 0) {
            return Result.fail(GeneralCode.Param_Error.getCode(), "请填写正确的炸药箱号");
        }
        if (request.getBoxTo2() != null && request.getBoxTo2() < 0) {
            return Result.fail(GeneralCode.Param_Error.getCode(), "请填写正确的炸药箱号");
        }
        if (request.getBoxTo3() != null && request.getBoxTo3() < 0) {
            return Result.fail(GeneralCode.Param_Error.getCode(), "请填写正确的炸药箱号");
        }
        if ((request.getBoxFrom1() != null && request.getBoxTo1() != null) && (request.getBoxFrom1() > request.getBoxTo1())) {
            return Result.fail(GeneralCode.Param_Error.getCode(), "终止值必须大于等于起始值");
        }
        if ((request.getBoxFrom2() != null && request.getBoxTo2() != null) && (request.getBoxFrom2() > request.getBoxTo2())) {
            return Result.fail(GeneralCode.Param_Error.getCode(), "终止值必须大于等于起始值");
        }
        if ((request.getBoxFrom3() != null && request.getBoxTo3() != null) && (request.getBoxFrom3() > request.getBoxTo3())) {
            return Result.fail(GeneralCode.Param_Error.getCode(), "终止值必须大于等于起始值");
        }
        if ((request.getBoxFrom1() != null || request.getBoxTo1() != null) && (StandardsEnums.getByCode(request.getType1()) == null)) {
            return Result.fail(GeneralCode.Param_Error.getCode(), "请选择炸药规格");
        }
        if ((request.getBoxFrom2() != null || request.getBoxTo2() != null) && (StandardsEnums.getByCode(request.getType2()) == null)) {
            return Result.fail(GeneralCode.Param_Error.getCode(), "请选择炸药规格");
        }
        if ((request.getBoxFrom3() != null || request.getBoxTo3() != null) && (StandardsEnums.getByCode(request.getType3()) == null)) {
            return Result.fail(GeneralCode.Param_Error.getCode(), "请选择炸药规格");
        }

        //交叉校验
        if (NumberCheck.in(request.getBoxFrom1(), request.getBoxFrom2(), request.getBoxTo2())||NumberCheck.in(request.getBoxFrom1(), request.getBoxFrom3(), request.getBoxTo3())) {
            return Result.fail(GeneralCode.Param_Error.getCode(), "数值不能交叉");
        }
        if (NumberCheck.in(request.getBoxTo1(), request.getBoxFrom2(), request.getBoxTo2())||NumberCheck.in(request.getBoxTo1(), request.getBoxFrom3(), request.getBoxTo3())) {
            return Result.fail(GeneralCode.Param_Error.getCode(), "数值不能交叉");
        }
        if (NumberCheck.in(request.getBoxFrom2(), request.getBoxFrom1(), request.getBoxTo1())||NumberCheck.in(request.getBoxFrom1(), request.getBoxFrom3(), request.getBoxTo3())) {
            return Result.fail(GeneralCode.Param_Error.getCode(), "数值不能交叉");
        }
        if (NumberCheck.in(request.getBoxTo2(), request.getBoxFrom1(), request.getBoxTo1())||NumberCheck.in(request.getBoxTo2(), request.getBoxFrom3(), request.getBoxTo3())) {
            return Result.fail(GeneralCode.Param_Error.getCode(), "数值不能交叉");
        }
        if (NumberCheck.in(request.getBoxFrom3(), request.getBoxFrom2(), request.getBoxTo2())||NumberCheck.in(request.getBoxFrom3(), request.getBoxFrom1(), request.getBoxTo1())) {
            return Result.fail(GeneralCode.Param_Error.getCode(), "数值不能交叉");
        }
        if (NumberCheck.in(request.getBoxTo3(), request.getBoxFrom2(), request.getBoxTo2())||NumberCheck.in(request.getBoxTo3(), request.getBoxFrom1(), request.getBoxTo1())) {
            return Result.fail(GeneralCode.Param_Error.getCode(), "数值不能交叉");
        }

        //入库，检查批次下的箱号是否存在
        Result result = service.checkIfExist(request.getBatchNum(), request.getBoxFrom1(), request.getBoxTo1());
        if (result.getCode() != GeneralCode.SUCCESS.getCode()) {
            return result;
        }
        result = service.checkIfExist(request.getBatchNum(), request.getBoxFrom2(), request.getBoxTo2());
        if (result.getCode() != GeneralCode.SUCCESS.getCode()) {
            return result;
        }
        result = service.checkIfExist(request.getBatchNum(), request.getBoxFrom3(), request.getBoxTo3());
        if (result.getCode() != GeneralCode.SUCCESS.getCode()) {
            return result;
        }
        int count = service.batchInsert(request,username);
        return Result.success(count);
    }

    /**
     * 按日期查账单
     */
    @RequestMapping(value = "/api/zhayao/log", method = RequestMethod.GET)
    public Result<ZhaYaoRecordResponse> getLog(@RequestParam(required = false) Long startTime,
                                               @RequestParam(required = false) Long endTime,
                                               @RequestParam(required = false) String keeper,
                                               @RequestParam(required = false) String consumer,
                                               @RequestParam(required = false) Integer pageNo,
                                               @RequestParam(required = false) Integer paseSize) {
        return Result.success(service.getLog(startTime, endTime, keeper, consumer, pageNo, paseSize));
    }

}
