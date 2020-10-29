package com.boom.success.service;

import com.boom.success.bo.ZhaYao;
import com.boom.success.bo.ZhaYaoLog;
import com.boom.success.consts.GeneralCode;
import com.boom.success.consts.Result;
import com.boom.success.consts.StandardsEnums;
import com.boom.success.consts.StatusEnums;
import com.boom.success.request.ZhaYaoBatchRequest;
import com.boom.success.response.ZhaYaoRecordResponse;
import com.boom.success.response.ZhaYaoResponse;
import com.boom.success.util.TimeUtil;
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
import java.util.Calendar;
import java.util.List;

@Service
public class ZhaYaoService {

    @Autowired
    private Dao dao;

    /**
     * 根据批次、箱号、柱号等查询炸药详情
     */
    public ZhaYaoResponse query(String batchNum, Integer boxFrom, Integer boxTo, Integer colFrom, Integer colTo, Integer status, Integer pageNo, Integer pageSize) {
        Cnd cnd = Cnd.NEW();
        if (!StringUtils.isEmpty(batchNum)) {
            cnd = cnd.and("batch_num", "like", "%" + batchNum + "%");
        }
        if (boxFrom != null) {
            cnd = cnd.and("box_num", ">=", boxFrom);
        }
        if (boxTo != null) {
            cnd = cnd.and("box_num", "<=", boxTo);
        }
        if (colFrom != null) {
            cnd = cnd.and("col_num", ">=", colFrom);
        }
        if (colTo != null) {
            cnd = cnd.and("col_num", "<=", colTo);
        }
        if (status != null) {
            cnd = cnd.and("status", "=", status);
        }
        if (pageNo == null || pageNo <= 0) {
            pageNo = 1;
        }
        if (pageSize == null || pageSize <= 0) {
            pageSize = 100;
        }
        OrderBy orderBy = cnd.desc("id");
        List<ZhaYao> list = dao.query(ZhaYao.class, orderBy, new Pager(pageNo, pageSize));
        int total = dao.count(ZhaYao.class, cnd);
        ZhaYaoResponse zhaYaoResponse = new ZhaYaoResponse();
        zhaYaoResponse.setTotal(total);
        zhaYaoResponse.setZhaYaoList(list);
        return zhaYaoResponse;
    }

    /**
     * 修改单个炸药信息
     */
    public boolean update(ZhaYao zhaYao) {
        if (zhaYao == null || zhaYao.getId() == null) {
            return false;
        }
        return dao.update(zhaYao) == 1;
    }

    /**
     * 记录操作日志日志
     */
    public void insertLog(ZhaYaoLog log) {
        dao.insert(log);
    }

    /**
     * 批量新增炸药信息，炸药柱号根据选择的规格来新增而不是让用户自己填写柱号
     */
    public int batchInsert(ZhaYaoBatchRequest request) {
        List<ZhaYao> list = new ArrayList<>();
        for (int i = request.getBoxFrom(); i <= request.getBoxTo(); i++) {
            float unit = StandardsEnums.getByCode(request.getType()).getUnit();
            for (int j = 1; j <= 24 / unit; j++) {
                ZhaYao zhaYao = new ZhaYao();
                zhaYao.setBatchNum(request.getBatchNum());
                zhaYao.setBoxNum(i);
                zhaYao.setColNum(j);
                zhaYao.setUnit(unit);
                zhaYao.setStoreTime(request.getDate());
                zhaYao.setStatus(StatusEnums.INIT.getCode());
                zhaYao.setKeeper(request.getKeeper());
                list.add(zhaYao);
            }
        }
        List<ZhaYao> zhaYaos = dao.insert(list);
        return zhaYaos.size();
    }

    /**
     * 批量新增前 检查是否已存在炸药信息
     */
    public Result checkIfExist(String batchNum, Integer boxFrom, Integer boxTo) {
        List<ZhaYao> zhaYaos = getZhaYaoList(batchNum, boxFrom, boxTo, null, null);
        if (CollectionUtils.isEmpty(zhaYaos)) {
            return Result.success(null);
        }
        return Result.fail(GeneralCode.Param_Error.getCode(), "炸药已存在！批次号：" + zhaYaos.get(0).getBatchNum() + ", 箱号:" + zhaYaos.get(0).getBoxNum());
    }

    /**
     * 批量修改前 检查是否已存在炸药信息
     */
    public Result checkIfExist2(String batchNum, Integer boxFrom, Integer boxTo, Integer colFrom, Integer colTo) {
        List<ZhaYao> zhaYaos = getZhaYaoList(batchNum, boxFrom, boxTo, colFrom, colTo);
        if (CollectionUtils.isEmpty(zhaYaos)) {
            return Result.fail(GeneralCode.Param_Error.getCode(), "炸药信息不存在");
        }
        return Result.success(zhaYaos.parallelStream().mapToDouble(ZhaYao::getUnit).sum());
    }

    /**
     * 批量修改
     */
    public int batchUpdate(ZhaYaoBatchRequest request) {
        Chain chain = Chain.make("status", request.getOptType()).add("consumer", request.getConsumer());
        if (request.getOptType() == StatusEnums.ON_GOING.getCode()) {
            chain.add("send_time", request.getDate());
        } else if (request.getOptType() == StatusEnums.BACK.getCode()) {
            chain.add("back_time", request.getDate());
        } else if (request.getOptType() == StatusEnums.CONSUMED.getCode()) {
            chain.add("use_time", request.getDate());
        } else {
            return 0;
        }
        Cnd cnd = Cnd.where("batch_num", "=", request.getBatchNum()).and("box_num", ">=", request.getBoxFrom()).
                and("box_num", "<=", request.getBoxTo());
        if (request.getColFrom() != null) {
            cnd = cnd.and("col_num", ">=", request.getColFrom());
        }
        if (request.getColTo() != null) {
            cnd = cnd.and("col_num", "<=", request.getColTo());
        }
        return dao.update(ZhaYao.class, chain, cnd);
    }

    private List<ZhaYao> getZhaYaoList(String batchNum, Integer boxFrom, Integer boxTo, Integer colFrom, Integer colTo) {
        Cnd cnd = Cnd.NEW();
        cnd = cnd.and("batch_num", "=", batchNum).and("box_num", ">=", boxFrom).and("box_num", "<=", boxTo);
        if (colFrom != null) {
            cnd = cnd.and("col_num", ">=", colFrom);
        }
        if (colTo != null) {
            cnd = cnd.and("col_num", "<=", colTo);
        }
        return dao.query(ZhaYao.class, cnd);
    }

    public ZhaYaoRecordResponse getLog(Long time, Integer pageNo, Integer pageSize) {
        if (pageNo == null || pageNo <= 0) {
            pageNo = 1;
        }
        if (pageSize == null || pageSize <= 0) {
            pageSize = 10;
        }

        ZhaYaoRecordResponse recordResponse = new ZhaYaoRecordResponse();
        List<ZhaYaoLog> logs;
        Cnd cnd = Cnd.NEW();
        int stock;
        if (time != null) {
            long start = TimeUtil.getStart(time);
            long end = TimeUtil.getEnd(time);
            cnd.and("date", ">=", start).and("date", "<=", end);
            logs = dao.query(ZhaYaoLog.class, cnd, new Pager(pageNo, pageSize));
            recordResponse.setTotal(dao.count(ZhaYaoLog.class, cnd));
            //当日库存计算： 当日之前的累计存入-当日之前的累计消耗
            cnd = Cnd.NEW();
            cnd.and("storeTime", "<", end);
            int stockSum = dao.func(ZhaYao.class, "sum", "unit", cnd);
            cnd = Cnd.NEW();
            cnd.and("use_time", "<", end);
            int useSum = dao.func(ZhaYao.class, "sum", "unit", cnd);
            stock = stockSum - useSum;
        }else{
            logs = dao.query(ZhaYaoLog.class, cnd, new Pager(pageNo, pageSize));
            int stockSum = dao.func(ZhaYao.class, "sum", "unit", cnd);
            cnd = Cnd.NEW();
            cnd.and("use_time", ">", 0);
            int useSum = dao.func(ZhaYao.class, "sum", "unit", cnd);
            stock = stockSum - useSum;
        }

        for (ZhaYaoLog e : logs) {
            float count = e.getCount();
            if (e.getOperation().equals(StatusEnums.INIT.getDesc())) {
                e.setStore(count);
            } else if (e.getOperation().equals(StatusEnums.ON_GOING.getDesc())) {
                e.setSend(count);
            } else if (e.getOperation().equals(StatusEnums.BACK.getDesc())) {
                e.setBack(count);
            } else if (e.getOperation().equals(StatusEnums.CONSUMED.getDesc())) {
                e.setConsumed(count);
            }
        }

        recordResponse.setRecords(logs);
        recordResponse.setStock(stock);
        return recordResponse;
    }
}

