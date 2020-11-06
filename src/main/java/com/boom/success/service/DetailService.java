package com.boom.success.service;

import com.boom.success.bo.Detail;
import com.boom.success.request.DetailResponse;
import com.boom.success.response.bo.DetailBo;
import com.boom.success.util.TimeUtil;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.OrderBy;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
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

    public DetailResponse query(Long date, Integer lineNum, String stakeNum, String fixCode, Integer childCode, String batchNum, Integer boxNum, String down, String packager, Integer pageNo, Integer pageSize) {
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
        if (!StringUtils.isEmpty(down)) {
            cnd = cnd.and("down", "like", "%" + down + "%");
        }
        if (!StringUtils.isEmpty(packager)) {
            cnd = cnd.and("packager", "like", "%" + packager + "%");
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
        if (pageNo == null || pageNo <= 0) {
            pageNo = 1;
        }
        if (pageSize == null || pageSize <= 0) {
            pageSize = 100;
        }
        OrderBy orderBy = cnd.desc("date").desc("id");
        List<Detail> list = dao.query(Detail.class, orderBy, new Pager(pageNo, pageSize));
        int total = dao.count(Detail.class, cnd);
        DetailResponse detailResponse = new DetailResponse();
        detailResponse.setTotal(total);
        List<DetailBo> detailBos = new ArrayList<>();
        detailResponse.setDetailList(detailBos);
        for (Detail e : list) {
            DetailBo bo = new DetailBo();
            BeanUtils.copyProperties(e, bo);
            bo.setChildCode(String.format("%05d", e.getChildCode()));
            bo.setBoxNum(String.format("%02d", e.getBoxNum()));
            detailBos.add(bo);
        }
        return detailResponse;
    }

    public boolean existLeiguan(String fixCode, int childCode) {
        String childCodeFor = String.format("%05d", childCode);
        Detail detail = dao.fetch(Detail.class, Cnd.where("fix_code", "=", fixCode).and("child_code", "=", childCodeFor));
        return detail != null;
    }

    public boolean existZhayao(String batchNum, int boxNum, String col) {
        Detail detail = dao.fetch(Detail.class, Cnd.where("batch_num", "=", batchNum).and("box_num", "=", boxNum).and("col_num", "like", "%"+col+"%"));
        return detail != null;
    }

    public List<DetailBo> query(Long date, Integer lineNum, String stakeNum, String fixCode, Integer childCode, String batchNum, Integer boxNum, String down, String packager) {
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
        if (!StringUtils.isEmpty(down)) {
            cnd = cnd.and("down", "like", "%" + down + "%");
        }
        if (!StringUtils.isEmpty(packager)) {
            cnd = cnd.and("packager", "like", "%" + packager + "%");
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
        OrderBy orderBy = cnd.desc("date").desc("id");
        List<Detail> list = dao.query(Detail.class, orderBy);
        List<DetailBo> detailBos = new ArrayList<>();
        for (Detail e : list) {
            DetailBo bo = new DetailBo();
            BeanUtils.copyProperties(e, bo);
            bo.setChildCode(String.format("%05d", e.getChildCode()));
            bo.setBoxNum(String.format("%02d", e.getBoxNum()));
            detailBos.add(bo);
        }
        return detailBos;
    }

}
