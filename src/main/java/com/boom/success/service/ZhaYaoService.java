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
import com.boom.success.response.bo.ZhaYaoBo;
import com.boom.success.response.bo.ZhaYaoLogBo;
import com.boom.success.util.TimeUtil;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.OrderBy;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class ZhaYaoService {

    @Autowired
    private Dao dao;

    /**
     * 根据批次、箱号、柱号等查询炸药详情
     */
    public ZhaYaoResponse query(String batchNum, Integer boxFrom, Integer boxTo, Integer colFrom, Integer colTo, Integer status, String keeper, String consumer, Integer pageNo, Integer pageSize) {
        Cnd cnd = Cnd.NEW();
        if (!StringUtils.isEmpty(batchNum)) {
            cnd = cnd.and("batch_num", "like", "%" + batchNum + "%");
        }
        if (!StringUtils.isEmpty(keeper)) {
            cnd = cnd.and("keeper", "like", "%" + keeper + "%");
        }
        if (!StringUtils.isEmpty(consumer)) {
            cnd = cnd.and("consumer", "like", "%" + consumer + "%");
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
        List<ZhaYaoBo> zhaYaoBos = new ArrayList<>();
        zhaYaoResponse.setZhaYaoList(zhaYaoBos);
        for (ZhaYao e : list) {
            ZhaYaoBo bo = new ZhaYaoBo();
            BeanUtils.copyProperties(e, bo);
            bo.setBoxNum(String.format("%04d", e.getBoxNum()));
            bo.setColNum(String.format("%02d", e.getColNum()));
            zhaYaoBos.add(bo);
        }
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
    public int batchInsert(ZhaYaoBatchRequest request,String username) {
        int count = 0;
        count += insertList(request.getDate(),request.getKeeper(),request.getBatchNum(),request.getBoxFrom1(),request.getBoxTo1(),request.getType1(),username);
        count += insertList(request.getDate(),request.getKeeper(),request.getBatchNum(),request.getBoxFrom2(),request.getBoxTo2(),request.getType2(),username);
        count += insertList(request.getDate(),request.getKeeper(),request.getBatchNum(),request.getBoxFrom3(),request.getBoxTo3(),request.getType3(),username);
        return count;
    }

    private int insertList(Long date, String keeper, String batchNum, Integer from, Integer to, Integer type, String username) {
        if (from == null && to == null) {
            return 0;
        }
        if (from == null && to != null) {
            from = to;
        } else if (from != null && to == null) {
            to = from;
        }
        List<ZhaYao> list = new ArrayList<>();
        StandardsEnums standardsEnums = StandardsEnums.getByCode(type);
        float unit =standardsEnums .getUnit();
        for (int i = from; i <= to; i++) {
            for (int j = 1; j <= 24 / unit; j++) {
                ZhaYao zhaYao = new ZhaYao();
                zhaYao.setBatchNum(batchNum);
                zhaYao.setBoxNum(i);
                zhaYao.setColNum(j);
                zhaYao.setUnit(unit);
                zhaYao.setStoreTime(date);
                zhaYao.setStatus(StatusEnums.INIT.getCode());
                zhaYao.setKeeper(keeper);
                list.add(zhaYao);
            }
        }
        List<ZhaYao> zhaYaos = dao.insert(list);
        //计入批量操作日志
        ZhaYaoLog log = new ZhaYaoLog();
        log.setDate(date);
        log.setOperator(username);
        log.setOperation(StatusEnums.INIT.getDesc());
        log.setBatchNum(batchNum);
        log.setBox(from==to?(from+""):(from+"-"+to));
        log.setCount((float) (24*(to-from+1)));
        log.setKeeper(keeper);
        insertLog(log);
        return zhaYaos.size();
    }

    /**
     * 批量新增前 检查是否已存在炸药信息
     */
    public Result checkIfExist(String batchNum, Integer boxFrom, Integer boxTo) {
        if (boxFrom == null && boxTo == null) {
            return Result.success(null);
        }
        if (boxFrom == null && boxTo != null) {
            boxFrom = boxTo;
        } else if (boxFrom != null && boxTo == null) {
            boxTo = boxFrom;
        }
        List<ZhaYao> zhaYaos = getZhaYaoList(batchNum, boxFrom, boxTo, null, null);
        if (CollectionUtils.isEmpty(zhaYaos)) {
            return Result.success(null);
        }
        return Result.fail(GeneralCode.Param_Error.getCode(), "炸药已存在！批次号：" + zhaYaos.get(0).getBatchNum() + ", 箱号:" + zhaYaos.get(0).getBoxNum());
    }

    /**
     * 批量修改前 检查是否已存在炸药信息
     */
    public Result<Double> checkIfExist2(String batchNum, Integer boxFrom, Integer boxTo, Integer colFrom, Integer colTo) {
        List<ZhaYao> zhaYaos = getZhaYaoList(batchNum, boxFrom, boxTo, colFrom, colTo);
        if (CollectionUtils.isEmpty(zhaYaos)) {
            return Result.fail(GeneralCode.Param_Error.getCode(), "炸药信息不存在");
        }
        return Result.success(zhaYaos.parallelStream().mapToDouble(ZhaYao::getUnit).sum());
    }

    /**
     * 批量修改
     */
    public int batchUpdate(Long date,Integer optType,String batchNum,Integer boxNum,Integer colFrom,Integer colTo,String consumer) {
        Chain chain = Chain.make("status", optType).add("consumer", consumer);
        if (optType == StatusEnums.ON_GOING.getCode()) {
            chain.add("send_time", date);
        } else if (optType == StatusEnums.BACK.getCode()) {
            chain.add("back_time", date);
        } else {
            return 0;
        }
        Cnd cnd = Cnd.where("batch_num", "=", batchNum).and("box_num", "=", boxNum);
        if (colFrom != null) {
            cnd = cnd.and("col_num", ">=", colFrom);
        }
        if (colTo != null) {
            cnd = cnd.and("col_num", "<=", colTo);
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

    public ZhaYao queryByBox(String batchNum, Integer box) {
        return dao.fetch(ZhaYao.class, Cnd.where("batch_num", "=", batchNum).and("box_num", "=", box));
    }

    public ZhaYaoRecordResponse getLog(Long startTime,Long endTime,String keeper,String consumer, Integer pageNo, Integer pageSize) {
        if (pageNo == null || pageNo <= 0) {
            pageNo = 1;
        }
        if (pageSize == null || pageSize <= 0) {
            pageSize = 10;
        }

        ZhaYaoRecordResponse recordResponse = new ZhaYaoRecordResponse();
        List<ZhaYaoLog> logs;
        Cnd cnd = Cnd.NEW();
        cnd.desc("date").desc("id");
        if (!StringUtils.isEmpty(keeper)) {
            cnd.and("keeper", "like", "%" + keeper + "%");
        }
        if (!StringUtils.isEmpty(consumer)) {
            cnd.and("consumer", "like", "%" + consumer + "%");
        }
        int stock;
        if (startTime != null || endTime != null) {
            if (startTime == null) {
                startTime = endTime;
            }
            if (endTime == null) {
                endTime = startTime;
            }
            long start = TimeUtil.getStart(startTime);
            long end = TimeUtil.getEnd(endTime);
            cnd.and("date", ">=", start).and("date", "<=", end);
            logs = dao.query(ZhaYaoLog.class, cnd, new Pager(pageNo, pageSize));
            recordResponse.setTotal(dao.count(ZhaYaoLog.class, cnd));
            //当日库存计算： 当日之前的累计存入-当日之前的累计消耗
            cnd = Cnd.NEW();
            cnd.and("storeTime", "<", end);
            int stockSum = dao.func(ZhaYao.class, "sum", "unit", cnd);
            cnd = Cnd.NEW();
            cnd.and("send_time", "<", end).and("status", "=", StatusEnums.ON_GOING.getCode());
            int useSum = dao.func(ZhaYao.class, "sum", "unit", cnd);
            stock = stockSum - useSum;
        }else{
            logs = dao.query(ZhaYaoLog.class, cnd, new Pager(pageNo, pageSize));
            recordResponse.setTotal(dao.count(ZhaYaoLog.class, cnd));
            cnd = Cnd.NEW();
            int stockSum = dao.func(ZhaYao.class, "sum", "unit", cnd);
            cnd.and("status", "=", StatusEnums.ON_GOING.getCode());
            int useSum = dao.func(ZhaYao.class, "sum", "unit", cnd);
            stock = stockSum - useSum;
        }

        List<ZhaYaoLogBo> zhaYaoLogBos = new ArrayList<>();
        for (ZhaYaoLog e : logs) {
            ZhaYaoLogBo bo = new ZhaYaoLogBo();
            BeanUtils.copyProperties(e, bo);
            float count = e.getCount();
            if (e.getOperation().equals(StatusEnums.INIT.getDesc())) {
                bo.setStore(count);
            } else if (e.getOperation().equals(StatusEnums.ON_GOING.getDesc())) {
                bo.setSend(count);
            } else if (e.getOperation().equals(StatusEnums.BACK.getDesc())) {
                bo.setBack(count);
            }
            zhaYaoLogBos.add(bo);
        }

        recordResponse.setRecords(zhaYaoLogBos);
        recordResponse.setStock(stock);
        return recordResponse;
    }
}

