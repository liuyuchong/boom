package com.boom.success.controller;

import com.boom.success.bo.User;
import com.boom.success.consts.GeneralCode;
import com.boom.success.consts.Result;
import com.boom.success.consts.RoleEnums;
import com.boom.success.request.AddMemberRequest;
import com.boom.success.request.LoginRequest;
import com.boom.success.request.ModifyPasswordRequest;
import com.boom.success.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/api")
public class LoginController {
    private final String phoneRegex = "^1[0-9]{10}$";
    private final String passwordRegex = "[a-zA-Z0-9_]{6,18}";

    @Autowired
    private LoginService loginService;

    /**
     * 登录
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Result<String> login(@RequestBody LoginRequest request) {
        if (request == null || StringUtils.isEmpty(request.getUsername()) || StringUtils.isEmpty(request.getPassword())) {
            return Result.fail(GeneralCode.Param_Error);
        }

        return loginService.login(request);
    }

    /**
     * 添加成员
     */
    @RequestMapping(value = "/addMember",method = RequestMethod.POST)
    public Result<String> add(@RequestBody AddMemberRequest request) {
        if (request == null || StringUtils.isEmpty(request.getUsername()) || StringUtils.isEmpty(request.getPhoneNumber())
                || null == RoleEnums.getRole(request.getRole())) {
            return Result.fail(GeneralCode.Param_Error);
        }
        if (!request.getPhoneNumber().matches(phoneRegex)) {
            return Result.fail(GeneralCode.Param_Error.getCode(), "手机号格式有误");
        }
        if (loginService.exist(request.getUsername())) {
            return Result.fail(GeneralCode.Param_Error.getCode(), "用户已存在");
        }

        return loginService.addMember(request);
    }

    /**
     * 修改密码
     */
    @RequestMapping(value = "/modifyPassword",method = RequestMethod.POST)
    public Result<Boolean> modify(@RequestBody ModifyPasswordRequest request) {
        if (request == null || request.getId() <= 0 || StringUtils.isEmpty(request.getOriginalPassword()) || StringUtils.isEmpty(request.getNewPassword())) {
            return Result.fail(GeneralCode.Param_Error);
        }
        if (Objects.equals(request.getOriginalPassword(), request.getNewPassword())) {
            return Result.fail(GeneralCode.Param_Error.getCode(),"新旧密码不能相同");
        }
        if (!request.getNewPassword().matches(passwordRegex)) {
            return Result.fail(GeneralCode.Param_Error.getCode(),"密码只能包含大小写字母或数字或下划线，且长度在6-18位之间");
        }
        User user = loginService.getById(request.getId());
        if (user == null) {
            return Result.fail(GeneralCode.Param_Error.getCode(), "用户id有误");
        }

        if (!Objects.equals(user.getPassword(), request.getOriginalPassword())) {
            return Result.fail(GeneralCode.Param_Error.getCode(), "原密码有误");
        }
        user.setPassword(request.getNewPassword());
        return Result.success(loginService.update(user));
    }
}
