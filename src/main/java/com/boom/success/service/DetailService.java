package com.boom.success.service;

import com.boom.success.bo.Detail;
import com.boom.success.request.DetailResponse;
import com.boom.success.util.TimeUtil;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.OrderBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Service
public class DetailService {

    @Autowired
    private Dao dao;

    public Detail insert(Detail detail) {
        return dao.insert(detail);
    }

    public Detail getById(Long id) {
        return dao.fetch(Detail.class, id);
    }

    public int update(Detail detail) {
        return dao.update(detail);
    }

    public DetailResponse query(Long date, Integer lineNum, String stakeNum, String fixCode, Integer childCode, String batchNum, Integer boxNum, Integer pageNo, Integer pageSize) {
        Cnd cnd = Cnd.NEW();
        if (date != null) {
            long start = TimeUtil.getStart(date);
            long end = TimeUtil.getEnd(date);
            cnd.and("date", ">=", start).and("date", "<=", end);
        }
        if (lineNum != null) {
            cnd.and("line_num", "=", lineNum);
        }
        if (!StringUtils.isEmpty(stakeNum)) {
            cnd.and("stake_num", "like", "%" + stakeNum + "%");
        }
        if (!StringUtils.isEmpty(fixCode)) {
            cnd.and("fix_code", "like", "%" + fixCode + "%");
        }
        if (childCode != null) {
            cnd.and("child_code", "=", childCode);
        }
        if (!StringUtils.isEmpty(batchNum)) {
            cnd.and("batch_num", "like", "%" + batchNum + "%");
        }
        if (boxNum != null) {
            cnd.and("box_num", "=", boxNum);
        }
        if (pageNo==null||pageNo <= 0) {
            pageNo = 1;
        }
        if (pageSize==null||pageSize <= 0) {
            pageSize = 100;
        }
        OrderBy orderBy = cnd.desc("id");
        List<Detail> list = dao.query(Detail.class, orderBy, new Pager(pageNo, pageSize));
        int total = dao.count(Detail.class, cnd);
        DetailResponse detailResponse = new DetailResponse();
        detailResponse.setTotal(total);
        detailResponse.setDetailList(list);
        return detailResponse;
    }

    public static void main(String[] args) {
        System.out.println(new Date().getTime());
    }
}
