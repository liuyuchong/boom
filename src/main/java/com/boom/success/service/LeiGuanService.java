package com.boom.success.service;

import com.boom.success.bo.LeiGuan;
import com.boom.success.bo.LeiGuanLog;
import com.boom.success.consts.GeneralCode;
import com.boom.success.consts.Result;
import com.boom.success.consts.StatusEnums;
import com.boom.success.request.LeiGuanBatchRequest;
import com.boom.success.response.LeiGuanResponse;
import com.boom.success.response.LeiguanRecordResponse;
import com.boom.success.response.bo.LeiGuanBo;
import com.boom.success.response.bo.LeiGuanLogBo;
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
    public LeiGuanResponse query(String fixCode, Integer childCode, Integer from, Integer to, Integer status, String keeper, String consumer, Integer pageNo, Integer pageSize) {
        Cnd cnd = Cnd.NEW();
        if (!StringUtils.isEmpty(fixCode)) {
            cnd = cnd.and("fix_code", "like", "%" + fixCode + "%");
        }
        if (!StringUtils.isEmpty(keeper)) {
            cnd = cnd.and("keeper", "like", "%" + keeper + "%");
        }
        if (!StringUtils.isEmpty(consumer)) {
            cnd = cnd.and("consumer", "like", "%" + consumer + "%");
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
        if (pageNo == null || pageNo <= 0) {
            pageNo = 1;
        }
        if (pageSize == null || pageSize <= 0) {
            pageSize = 100;
        }
        OrderBy orderBy = cnd.desc("id");
        List<LeiGuan> list = dao.query(LeiGuan.class, orderBy, new Pager(pageNo, pageSize));
        int total = dao.count(LeiGuan.class, cnd);
        LeiGuanResponse leiGuanResponse = new LeiGuanResponse();
        leiGuanResponse.setTotal(total);
        List<LeiGuanBo> leiGuanBos = new ArrayList<>();
        leiGuanResponse.setLeiGuanList(leiGuanBos);
        for (LeiGuan e : list) {
            LeiGuanBo bo = new LeiGuanBo();
            BeanUtils.copyProperties(e, bo);
            bo.setChildCode(String.format("%05d", e.getChildCode()));
            leiGuanBos.add(bo);
        }
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
    public int batchInsert(LeiGuanBatchRequest request,String username) {
        int count = 0;
        count += insertList(request.getDate(), request.getKeeper(), request.getFixCode(), request.getFrom1(), request.getTo1(),username);
        count += insertList(request.getDate(), request.getKeeper(), request.getFixCode(), request.getFrom2(), request.getTo2(),username);
        count += insertList(request.getDate(), request.getKeeper(), request.getFixCode(), request.getFrom3(), request.getTo3(),username);
        return count;
    }

    private int insertList(Long date, String keeper, String fixCode, Integer from, Integer to,String username) {
        if (from == null && to == null) {
            return 0;
        }
        if (from == null && to != null) {
            from = to;
        } else if (from != null && to == null) {
            to = from;
        }
        List<LeiGuan> list = new ArrayList<>();
        for (int i = from; i <= to; i++) {
            LeiGuan leiGuan = new LeiGuan();
            leiGuan.setStoreTime(date);
            leiGuan.setStatus(StatusEnums.INIT.getCode());
            leiGuan.setKeeper(keeper);
            leiGuan.setFixCode(fixCode);
            leiGuan.setChildCode(i);
            list.add(leiGuan);
        }
        List<LeiGuan> leiGuans = dao.fastInsert(list);

        //计入批量操作日志
        LeiGuanLog log = new LeiGuanLog();
        log.setDate(date);
        log.setOperator(username);
        log.setOperation(StatusEnums.INIT.getDesc());
        log.setFixCode(fixCode);
        log.setFrom(from);
        log.setTo(to);
        log.setKeeper(keeper);
        insertLog(log);
        return leiGuans.size();
    }

    /**
     * 批量新增前 检查是否已存在雷管信息
     */
    public Result checkIfExist(String fixCode, Integer from, Integer to) {
        if (from == null && to == null) {
            return Result.success(null);
        }
        if (from == null && to != null) {
            from = to;
        } else if (from != null && to == null) {
            to = from;
        }
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
        if (from == null && to == null) {
            return Result.success(null);
        }
        if (from == null && to != null) {
            from = to;
        } else if (from != null && to == null) {
            to = from;
        }
        List<LeiGuan> leiGuans = getLeiGuanList(fixCode, from, to);
        if (CollectionUtils.isEmpty(leiGuans)) {
            return Result.fail(GeneralCode.Param_Error.getCode(),"雷管不存在！固定码："+fixCode+",发码："+from);
        }
        return Result.success(null);
    }

    /**
     * 批量修改
     */
    public int batchUpdate(LeiGuanBatchRequest request,String username) {
        int count = 0;
        String desc;
        Chain chain = Chain.make("status", request.getOptType()).add("consumer",request.getConsumer());
        if (request.getOptType() == StatusEnums.ON_GOING.getCode()) {
            chain.add("send_time", request.getDate());
            desc = StatusEnums.ON_GOING.getDesc();
        } else if (request.getOptType() == StatusEnums.BACK.getCode()) {
            chain.add("back_time", request.getDate());
            desc = StatusEnums.BACK.getDesc();
        } else {
            return 0;
        }
        count += update(chain, request.getFixCode(), request.getFrom1(), request.getTo1(),request.getDate(),username,request.getConsumer(),desc);
        count += update(chain, request.getFixCode(), request.getFrom2(), request.getTo2(),request.getDate(),username,request.getConsumer(),desc);
        count += update(chain, request.getFixCode(), request.getFrom3(), request.getTo3(),request.getDate(),username,request.getConsumer(),desc);
        return count;
    }

    private int update(Chain chain, String fixCode, Integer from, Integer to,Long date,String username,String consumer,String optDesc) {
        if (from == null && to == null) {
            return 0;
        }
        if (from == null && to != null) {
            from = to;
        } else if (from != null && to == null) {
            to = from;
        }
        int count = dao.update(LeiGuan.class, chain, Cnd.where("fix_code", "=", fixCode).and("child_code", ">=", from).
                and("child_code", "<=", to));
        //计入批量操作日志
        LeiGuanLog log = new LeiGuanLog();
        log.setDate(date);
        log.setOperator(username);
        log.setOperation(optDesc);
        log.setFixCode(fixCode);
        log.setFrom(from);
        log.setTo(to);
        log.setConsumer(consumer);
        insertLog(log);
        return count;
    }

    private List<LeiGuan> getLeiGuanList(String fixCode, Integer from, Integer to) {
        Cnd cnd = Cnd.NEW();
        cnd = cnd.and("fix_code", "=", fixCode).and("child_code", ">=", from).and("child_code", "<=", to);
        return dao.query(LeiGuan.class, cnd);
    }

    public LeiGuan queryByCode(String fixCode, Integer childCode) {
        if (StringUtils.isEmpty(fixCode)) {
            return null;
        }
        if (childCode == null || childCode < 0) {
            return null;
        }
        return dao.fetch(LeiGuan.class, Cnd.where("fix_code", "=", fixCode).and("child_code", "=", childCode));
    }

    public LeiguanRecordResponse getLog(Long startTime, Long endTime, String keeper, String consumer, Integer pageNo, Integer pageSize) {
        if (pageNo==null||pageNo <= 0) {
            pageNo = 1;
        }
        if (pageSize==null||pageSize <= 0) {
            pageSize = 10;
        }
        LeiguanRecordResponse recordResponse = new LeiguanRecordResponse();
        List<LeiGuanLog> logs;
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
            logs = dao.query(LeiGuanLog.class, cnd, new Pager(pageNo, pageSize));
            recordResponse.setTotal(dao.count(LeiGuanLog.class, cnd));
            //当日库存计算： 当日之前的累计存入-当日之前的累计消耗
            cnd = Cnd.NEW();
            cnd.and("storeTime", "<", end);
            int stockSum = dao.count(LeiGuan.class, cnd);
            cnd = Cnd.NEW();
            cnd.and("send_time", "<", end).and("status", "=", StatusEnums.ON_GOING.getCode());
            int useSum = dao.count(LeiGuan.class, cnd);
            stock = stockSum - useSum;
        } else {
            logs = dao.query(LeiGuanLog.class, cnd, new Pager(pageNo, pageSize));
            recordResponse.setTotal(dao.count(LeiGuanLog.class, cnd));
            cnd = Cnd.NEW();
            int stockSum = dao.count(LeiGuan.class, cnd);
            cnd.and("status", "=", StatusEnums.ON_GOING.getCode());
            int useSum = dao.count(LeiGuan.class, cnd);
            stock = stockSum - useSum;
        }
        List<LeiGuanLogBo> leiGuanLogBos = new ArrayList<>();
        for (LeiGuanLog e : logs) {
            LeiGuanLogBo bo = new LeiGuanLogBo();
            BeanUtils.copyProperties(e, bo);
            int count = e.getTo() - e.getFrom() + 1;
            if (e.getOperation().equals(StatusEnums.INIT.getDesc())) {
                bo.setStore(count);
            }else if (e.getOperation().equals(StatusEnums.ON_GOING.getDesc())) {
                bo.setSend(count);
            }else if (e.getOperation().equals(StatusEnums.BACK.getDesc())) {
                bo.setBack(count);
            }
            bo.setFrom(String.format("%05d", e.getFrom()));
            bo.setTo(String.format("%05d", e.getTo()));
            leiGuanLogBos.add(bo);
        }
        recordResponse.setRecords(leiGuanLogBos);
        recordResponse.setStock(stock);
        return recordResponse;
    }
}

