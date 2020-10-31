package com.boom.success.controller;

import com.boom.success.bo.Detail;
import com.boom.success.bo.LeiGuan;
import com.boom.success.bo.Video;
import com.boom.success.consts.GeneralCode;
import com.boom.success.consts.Result;
import com.boom.success.consts.VideoTypeEnums;
import com.boom.success.request.DetailResponse;
import com.boom.success.request.UrlUpdateReqeust;
import com.boom.success.service.DetailService;
import com.boom.success.service.LeiGuanService;
import com.boom.success.service.ZhaYaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
public class DetailController {

    @Autowired
    private DetailService detailService;

    @Autowired
    private LeiGuanService leiGuanService;

    @Autowired
    private ZhaYaoService zhaYaoService;

    @RequestMapping(value = "/api/detail/query", method = RequestMethod.GET)
    public Result<DetailResponse> query(@RequestParam(required = false) Integer pageNo) {
        return null;
    }

    /**
     * 新增耗材明细
     *
     * @param detail
     * @return
     */
    @RequestMapping(value = "/api/detail", method = RequestMethod.POST)
    public Result<Detail> add(@RequestBody Detail detail) {
        if (detail == null) {
            return Result.fail(GeneralCode.Param_Error);
        }
        if (detail.getDate() == null || detail.getDate() < 0) {
            return Result.fail(GeneralCode.Param_Error.getCode(), "请填写日期");
        }
        if (detail.getLineNum() == null || detail.getLineNum() < 0) {
            return Result.fail(GeneralCode.Param_Error.getCode(), "请填写线束号");
        }
        if (StringUtils.isEmpty(detail.getStakeNum())) {
            return Result.fail(GeneralCode.Param_Error.getCode(), "请填写桩号");
        }
        if (detail.getHeight() == null || detail.getHeight() < 0) {
            return Result.fail(GeneralCode.Param_Error.getCode(), "请填写井深");
        }
        if (StringUtils.isEmpty(detail.getFixCode())) {
            return Result.fail(GeneralCode.Param_Error.getCode(), "请填写雷管固定码");
        }
        if (detail.getChildCode() == null || detail.getChildCode() < 0) {
            return Result.fail(GeneralCode.Param_Error.getCode(), "请填写雷管发码");
        }
        if (StringUtils.isEmpty(detail.getBatchNum())) {
            return Result.fail(GeneralCode.Param_Error.getCode(), "请填写炸药批次号");
        }
        if (detail.getBoxNum() == null || detail.getBoxNum() < 0) {
            return Result.fail(GeneralCode.Param_Error.getCode(), "请填写炸药箱号");
        }
        if (StringUtils.isEmpty(detail.getColNum())) {
            return Result.fail(GeneralCode.Param_Error.getCode(), "请填写炸药柱码，如2-3");
        }
        if (StringUtils.isEmpty(detail.getDown())) {
            return Result.fail(GeneralCode.Param_Error.getCode(), "请填写下药工姓名");
        }
        if (StringUtils.isEmpty(detail.getPackager())) {
            return Result.fail(GeneralCode.Param_Error.getCode(), "请填写包药工姓名");
        }

        //炸药信息检查
        Result<Double> zhaYaoInfo = checkZhaYao(detail.getBatchNum(), detail.getBoxNum(), detail.getColNum());
        if (zhaYaoInfo.getCode() != GeneralCode.SUCCESS.getCode()) {
            return Result.fail(zhaYaoInfo.getCode(), zhaYaoInfo.getMsg());
        }
        detail.setCount(zhaYaoInfo.getData().floatValue());

        //雷管信息检查
        LeiGuan leiGuan = leiGuanService.queryByCode(detail.getFixCode(), detail.getChildCode());
        if (leiGuan == null) {
            return Result.fail(GeneralCode.Param_Error.getCode(), "雷管信息不存在！");
        }

        // TODO: 2020/10/31 炸药雷管可用性检查
        return Result.success(detailService.insert(detail));
    }

    private Result<Double> checkZhaYao(String batchNum, Integer boxNum, String colNum) {
         String[] colNums = colNum.split("-");
        Integer from;
        Integer to = null;
        try {
            from = Integer.valueOf(colNums[0]);
            if (colNums.length > 1) {
                to = Integer.valueOf(colNums[1]);
            }
        } catch (Exception e) {
            return Result.fail(GeneralCode.Param_Error.getCode(), "请填写正确的炸药柱码信息，如2-3");
        }
        return zhaYaoService.checkIfExist2(batchNum, boxNum, boxNum, from, to);
    }

    /**
     * 更新耗材明细
     *
     * @param detail
     * @return
     */
    @RequestMapping(value = "/api/detail", method = RequestMethod.PUT)
    public Result<Detail> update(@RequestBody Detail detail) {
        if (detail == null) {
            return Result.fail(GeneralCode.Param_Error);
        }
        if (detail.getId() == null) {
            return Result.fail(GeneralCode.Param_Error.getCode(),"请填写耗材信息主键id");
        }
        Detail oldDetail = detailService.getById(detail.getId());
        if (oldDetail == null) {
            return Result.fail(GeneralCode.Param_Error.getCode(), "耗材信息不存在！");
        }
        if (detail.getDate() != null && detail.getDate() > 0&& detail.getDate().longValue() != oldDetail.getDate()) {
            oldDetail.setDate(detail.getDate());
        }
        if (detail.getLineNum() != null && detail.getLineNum().intValue() != oldDetail.getLineNum()) {
            oldDetail.setLineNum(detail.getLineNum());
        }
        if (!StringUtils.isEmpty(detail.getStakeNum()) && !detail.getStakeNum().equals(oldDetail.getStakeNum())) {
            oldDetail.setStakeNum(detail.getStakeNum());
        }
        if (detail.getHeight() != null && detail.getHeight() >= 0 && detail.getHeight().intValue() != oldDetail.getHeight()) {
            oldDetail.setHeight(detail.getHeight());
        }
        boolean updateLeiguanInfo = false;
        if (!StringUtils.isEmpty(detail.getFixCode()) && !detail.getFixCode().equals(oldDetail.getFixCode())) {
            oldDetail.setFixCode(detail.getFixCode());
            updateLeiguanInfo = true;
        }
        if (detail.getChildCode() != null && detail.getChildCode().intValue() != oldDetail.getChildCode()) {
            oldDetail.setChildCode(detail.getChildCode());
            updateLeiguanInfo = true;
        }
        //雷管信息检查
        if (updateLeiguanInfo) {
            LeiGuan leiGuan = leiGuanService.queryByCode(oldDetail.getFixCode(), oldDetail.getChildCode());
            if (leiGuan == null) {
                return Result.fail(GeneralCode.Param_Error.getCode(), "雷管信息不存在！");
            }
        }

        //炸药信息检查
        boolean updateZhaYaoInfo = false;
        if (!StringUtils.isEmpty(detail.getBatchNum()) && !detail.getBatchNum().equals(oldDetail.getBatchNum())) {
            oldDetail.setBatchNum(detail.getBatchNum());
            updateZhaYaoInfo = true;
        }
        if (detail.getBoxNum() != null && detail.getBoxNum() >= 0 && detail.getBoxNum().intValue() != oldDetail.getBoxNum()) {
            oldDetail.setBoxNum(detail.getBoxNum());
            updateZhaYaoInfo = true;
        }
        if (!StringUtils.isEmpty(detail.getColNum()) && !detail.getColNum().equals(oldDetail.getColNum())) {
            oldDetail.setColNum(detail.getColNum());
            updateZhaYaoInfo = true;
        }
        if (updateZhaYaoInfo) {
            //炸药信息检查
            Result<Double> zhaYaoInfo = checkZhaYao(oldDetail.getBatchNum(), oldDetail.getBoxNum(), oldDetail.getColNum());
            if (zhaYaoInfo.getCode() != GeneralCode.SUCCESS.getCode()) {
                return Result.fail(zhaYaoInfo.getCode(), "炸药信息不存在！");
            }
            detail.setCount(zhaYaoInfo.getData().floatValue());
        }

        if (!StringUtils.isEmpty(detail.getDown()) && !detail.getDown().equals(oldDetail.getDown())) {
            oldDetail.setDown(detail.getDown());
        }
        if (!StringUtils.isEmpty(detail.getPackager()) && !detail.getPackager().equals(oldDetail.getPackager())) {
            oldDetail.setPackager(detail.getPackager());
        }
        oldDetail.setMark(detail.getMark());
        if (detail.getVideos() != null) {
            oldDetail.setVideos(detail.getVideos());
        }
        detailService.update(oldDetail);
        return Result.success(oldDetail);
    }

    /**
     * 更新耗材视频
     */
    @RequestMapping(value = "/api/video/update", method = RequestMethod.POST)
    public Result<Detail> updateVideos(@RequestBody UrlUpdateReqeust reqeust) {
        if (reqeust == null) {
            return Result.fail(GeneralCode.Param_Error);
        }
        VideoTypeEnums type = VideoTypeEnums.get(reqeust.getType());
        if (type == null) {
            return Result.fail(GeneralCode.Param_Error.getCode(),"请选择正确的视频类型");
        }
        Detail oldDetail = detailService.getById(reqeust.getDetailId());
        if (oldDetail == null) {
            return Result.fail(GeneralCode.Param_Error.getCode(), "耗材信息不存在！");
        }
        Video videos = oldDetail.getVideos();
        if (videos == null) {
            videos = new Video();
        }
        switch (type) {
            case GUARD:
                videos.setGuardVideo(reqeust.getUrl());
                break;
            case SEND:
                videos.setSendVideo(reqeust.getUrl());
                break;
            case PACKAGE:
                videos.setPackageVideo(reqeust.getUrl());
                break;
            case CONSUME:
                videos.setUseVideo(reqeust.getUrl());
                break;
            case BAD:
                videos.setBadVideo(reqeust.getUrl());
        }
        oldDetail.setVideos(videos);
        detailService.update(oldDetail);
        return Result.success(oldDetail);
    }
}
