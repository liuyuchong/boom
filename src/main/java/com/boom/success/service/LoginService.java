package com.boom.success.service;

import com.boom.success.bo.User;
import com.boom.success.consts.GeneralCode;
import com.boom.success.consts.Result;
import com.boom.success.request.AddUserRequest;
import com.boom.success.request.LoginRequest;
import com.boom.success.response.UserInfo;
import com.boom.success.response.UserResponse;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.pager.Pager;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class LoginService {

    @Autowired
    private Dao dao;


    public Result<UserResponse> login(LoginRequest request) {
        User user = getUserByName(request.getUsername());
        if (user==null){
            return Result.fail(GeneralCode.Param_Error.getCode(), "账号不存在");
        }

        if (!Objects.equals(user.getPassword(),request.getPassword())) {
            return Result.fail(GeneralCode.Param_Error.getCode(), "账号或密码有误");
        }
        UserResponse userResponse = new UserResponse();
        BeanUtils.copyProperties(user, userResponse);
        return Result.success(userResponse);
    }

    public User getUserByName(String username) {
        return dao.fetch(User.class, Cnd.where("username", "=", username));
    }

    public boolean exist(String username) {
        return dao.fetch(User.class, Cnd.where("username", "=", username))!=null;
    }

    public User getById(int id) {
        return dao.fetch(User.class, id);
    }

    public Result<Boolean> addMember(AddUserRequest request) {
        User user = new User();
        BeanUtils.copyProperties(request, user);
        //手机号后四位为密码
        String phoneNumber = request.getPhoneNumber();
        user.setPassword(phoneNumber.substring(phoneNumber.length() - 4));
        dao.insert(user);
        return Result.success(true);
    }

    public boolean update(User user) {
        return dao.update(user)==1;
    }

    public Result<UserInfo> getUsers(String username, Integer pageSize, Integer pageNo) {
        Cnd condition = Cnd.NEW();
        if (!StringUtils.isEmpty(username)) {
            condition = condition.and("username", "like", "%" + username + "%");
        }
        if (pageSize == null || pageSize < 0) {
            pageSize = 10;
        }
        if (pageNo == null || pageNo < 0) {
            pageNo = 1;
        }
        Pager pager = new Pager(pageNo, pageSize);
        List<User> users = dao.query(User.class, condition, pager);
        int total = dao.count(User.class);
        UserInfo userInfo = new UserInfo();
        userInfo.setTotal(total);
        if (CollectionUtils.isEmpty(users)) {
            userInfo.setUsers(new ArrayList<>());
            return Result.success(userInfo);
        }
        List<UserResponse> userList = new ArrayList<>(users.size());
        for (User e : users) {
            UserResponse response = new UserResponse();
            BeanUtils.copyProperties(e, response);
            userList.add(response);
        }
        userInfo.setUsers(userList);
        return Result.success(userInfo);
    }
}
