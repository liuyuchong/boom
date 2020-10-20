package com.boom.success.service;

import com.boom.success.bo.User;
import com.boom.success.consts.GeneralCode;
import com.boom.success.consts.Result;
import com.boom.success.request.AddMemberRequest;
import com.boom.success.request.LoginRequest;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private Dao dao;


    public Result<String> login(LoginRequest request) {
        User user = dao.fetch(User.class, Cnd.where("username", "=", request.getUsername()).and("password", "=", request.getPassword()));
        if (user == null) {
            return Result.fail(GeneralCode.Param_Error.getCode(),"账号或密码有误");
        }
        return Result.success("login sucess");
    }

    public boolean exist(String username) {
        return dao.fetch(User.class, Cnd.where("username", "=", username))!=null;
    }

    public User getById(int id) {
        return dao.fetch(User.class, id);
    }

    public Result<String> addMember(AddMemberRequest request) {
        User user = new User();
        BeanUtils.copyProperties(request, user);
        //手机号后四位为密码
        String phoneNumber = request.getPhoneNumber();
        user.setPassword(phoneNumber.substring(phoneNumber.length() - 4));
        dao.insert(user);
        return Result.success(null);
    }

    public boolean update(User user) {
        return dao.update(user)==1;
    }
}
