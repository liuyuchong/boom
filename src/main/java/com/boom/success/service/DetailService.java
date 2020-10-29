package com.boom.success.service;

import com.boom.success.bo.Detail;
import org.nutz.dao.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetailService {

    @Autowired
    private Dao dao;

    public void insert(Detail detail) {
        dao.insert(detail);
    }

    public int update(Detail detail) {
        return dao.update(detail);
    }

    public List<Detail> query()

}
