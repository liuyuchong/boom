package com.boom.success.service;

import com.boom.success.bo.LeiGuan;
import com.boom.success.bo.LeiGuanLog;
import com.boom.success.consts.GeneralCode;
import com.boom.success.consts.LeiGuanStatusEnums;
import com.boom.success.consts.Result;
import com.boom.success.request.LeiGuanBatchRequest;
import com.boom.success.response.LeiGuanResponse;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.OrderBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class LeiGuanService {

    @Autowired
    private Dao dao;

    /**
     * 根据固定码、发码、状态等查询雷管详情
     *
     * @param fixCode
     * @param childCode
     * @param status
     * @param pageNo
     * @param pageSize
     * @return
     */
    public LeiGuanResponse query(String fixCode, Integer childCode, Integer from, Integer to, Integer status, Integer pageNo, Integer pageSize) {
        Cnd cnd = Cnd.NEW();
        if (!StringUtils.isEmpty(fixCode)) {
            cnd = cnd.and("fix_code", "like", "%" + fixCode + "%");
        }
        if (childCode != null) {
            cnd = cnd.and("child_code", "=", childCode);
        }
        if (from != null) {
            cnd = cnd.and("child_code", ">=", from);
        }
        if (to != null) {
            cnd = cnd.and("child_code", "<=", to);
        }
        if (status != null) {
            cnd = cnd.and("status", "=", status);
        }
        if (pageNo==null||pageNo <= 0) {
            pageNo = 1;
        }
        if (pageSize==null||pageSize <= 0) {
            pageSize = 100;
        }
        OrderBy orderBy = cnd.desc("fix_code").desc("child_code");
        List<LeiGuan> list = dao.query(LeiGuan.class, orderBy, new Pager(pageNo, pageSize));
        int total = dao.count(LeiGuan.class, cnd);
        LeiGuanResponse leiGuanResponse = new LeiGuanResponse();
        leiGuanResponse.setTotal(total);
        leiGuanResponse.setLeiGuanList(list);
        return leiGuanResponse;
    }

    /**
     * 修改单个雷管信息
     */
    public boolean update(LeiGuan leiGuan) {
        if (leiGuan == null || leiGuan.getId() == null) {
            return false;
        }
        return dao.update(leiGuan)==1;
    }

    /**
     * 记录操作日志日志
     */
    public void insertLog(LeiGuanLog log) {
        dao.insert(log);
    }

    /**
     * 批量新增雷管信息
     */
    public int batchInsert(LeiGuanBatchRequest request) {
        List<LeiGuan> list = new ArrayList<>();
        for (int i = request.getFrom(); i <= request.getTo(); i++) {
            LeiGuan leiGuan = new LeiGuan();
            leiGuan.setStoreTime(request.getDate());
            leiGuan.setStatus(LeiGuanStatusEnums.INIT.getCode());
            leiGuan.setKeeper(request.getKeeper());
            leiGuan.setFixCode(request.getFixCode());
            leiGuan.setChildCode(i);
            list.add(leiGuan);
        }
        List<LeiGuan> leiGuans = dao.insert(list);
        return leiGuans.size();
    }

    /**
     * 批量新增前 检查是否已存在雷管信息
     */
    public Result checkIfExist(String fixCode, Integer from, Integer to) {
        List<LeiGuan> leiGuans = getLeiGuanList(fixCode, from, to);
        if (CollectionUtils.isEmpty(leiGuans)) {
            return Result.success(null);
        }
        return Result.fail(GeneralCode.Param_Error.getCode(), "雷管已存在！固定码：" + leiGuans.get(0).getFixCode() + ",发码:" + leiGuans.get(0).getChildCode());
    }

    /**
     * 批量修改前 检查是否已存在雷管信息
     */
    public Result checkIfExist2(String fixCode, Integer from, Integer to) {
        List<LeiGuan> leiGuans = getLeiGuanList(fixCode, from, to);
        if (CollectionUtils.isEmpty(leiGuans)) {
            return Result.fail(GeneralCode.Param_Error.getCode(),"雷管信息不存在");
        }
        return Result.success(null);
    }

    /**
     * 批量修改
     */
    public int batchUpdate(LeiGuanBatchRequest request) {
        Chain chain = Chain.make("status", request.getOptType());
        if (request.getOptType() == LeiGuanStatusEnums.ON_GOING.getCode()) {
            chain.add("send_time", request.getDate());
        } else if (request.getOptType() == LeiGuanStatusEnums.BACK.getCode()) {
            chain.add("back_time", request.getDate());
        } else if (request.getOptType() == LeiGuanStatusEnums.BACK.getCode()) {
            chain.add("use_time", request.getDate());
        } else {
            return 0;
        }
        int count = dao.update(LeiGuan.class, chain, Cnd.where("fix_code", "=", request.getFixCode()).and("child_code", ">=", request.getFrom()).
                and("child_code", "<=", request.getTo()));
        return count;
    }

    private List<LeiGuan> getLeiGuanList(String fixCode, Integer from, Integer to) {
        Cnd cnd = Cnd.NEW();
        cnd = cnd.and("fix_code", "=", fixCode).and("child_code", ">=", from).and("child_code", "<=", to);
        return dao.query(LeiGuan.class, cnd);
    }
}
