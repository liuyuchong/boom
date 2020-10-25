package com.boom.success.controller;

import com.boom.success.bo.User;
import com.boom.success.consts.GeneralCode;
import com.boom.success.consts.Result;
import com.boom.success.consts.RoleEnums;
import com.boom.success.request.AddUserRequest;
import com.boom.success.request.LoginRequest;
import com.boom.success.request.ModifyPasswordRequest;
import com.boom.success.request.UpdateUserRequest;
import com.boom.success.response.UserInfo;
import com.boom.success.response.UserResponse;
import com.boom.success.service.LoginService;
import com.boom.success.util.LoginUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

import static com.boom.success.consts.GeneralCode.NOT_AUTHORIZED;

@RestController
public class LoginController {
    private final String phoneRegex = "^1[0-9]{10}$";
    private final String passwordRegex = "[a-zA-Z0-9_]{6,18}";

    @Autowired
    private LoginService loginService;

    /**
     * 登录
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Result login(HttpServletRequest httpServletRequest, @RequestBody LoginRequest request) {
        if (request == null || StringUtils.isEmpty(request.getUsername()) || StringUtils.isEmpty(request.getPassword())) {
            return Result.fail(GeneralCode.Param_Error);
        }
        Result<UserResponse> result = loginService.login(request);
        if (result.getCode() != GeneralCode.SUCCESS.getCode()) {
            return result;
        }
        httpServletRequest.getSession().setAttribute("username", request.getUsername());
        return result;
    }

    /**
     * 登录
     */
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public Result login2(String username,String password,HttpServletRequest httpServletRequest) {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return Result.fail(GeneralCode.Param_Error);
        }
        LoginRequest request = new LoginRequest();
        request.setUsername(username);
        request.setPassword(password);
        Result result = loginService.login(request);
        if (result.getCode() != GeneralCode.SUCCESS.getCode()) {
            return result;
        }
        httpServletRequest.getSession().setAttribute("username", request.getUsername());
        return Result.success("login sucecess");
    }

    /**
     * 登出
     */
    @RequestMapping("/logout")
    public Result<String> logout(HttpServletRequest request){
        request.getSession().invalidate();
        return Result.success("logout successfully");
    }

    /**
     * 添加成员
     */
    @RequestMapping(value = "/api/user",method = RequestMethod.POST)
    public Result<String> add(@RequestBody AddUserRequest request) {
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
     * 修改成员
     */
    @RequestMapping(value = "/api/user",method = RequestMethod.PUT)
    public Result<Boolean> update(@RequestBody UpdateUserRequest request) {
        if (request == null || request.getId()==null) {
            return Result.fail(GeneralCode.Param_Error);
        }
        if (RoleEnums.getRole(request.getRole()) == null) {
            return Result.fail(GeneralCode.Param_Error.getCode(), "角色有误");
        }
        if (StringUtils.isEmpty(request.getPhoneNumber())||!request.getPhoneNumber().matches(phoneRegex)) {
            return Result.fail(GeneralCode.Param_Error.getCode(), "手机号格式有误");
        }

        User user = loginService.getById(request.getId());
        if (user == null) {
            return Result.fail(GeneralCode.Param_Error.getCode(), "用户不存在");
        }
        BeanUtils.copyProperties(request,user);
        return Result.success(loginService.update(user));
    }

    /**
     * 获取成员列表
     */
    @RequestMapping(value = "/api/userList", method = RequestMethod.GET)
    public Result<UserInfo> getUsers(@RequestParam(required = false) String username,
                                     @RequestParam(required = false) Integer pageSize,
                                     @RequestParam(required = false) Integer pageNo) {
        return loginService.getUsers(username, pageSize, pageNo);
    }

    /**
     * 获取个人信息
     */
    @RequestMapping(value = "/api/own", method = RequestMethod.GET)
    public Result<UserResponse> getUser(HttpServletRequest request) {
        String username = LoginUtil.getUsername(request);
        User user = loginService.getUserByName(username);
        if (user == null) {
            return Result.fail(NOT_AUTHORIZED);
        }
        UserResponse response = new UserResponse();
        BeanUtils.copyProperties(user,response);
        return Result.success(response);
    }

    /**
     * 修改密码
     */
    @RequestMapping(value = "/api/modifyPassword", method = RequestMethod.POST)
    public Result<Boolean> modify(@RequestBody ModifyPasswordRequest request) {
        if (request == null || request.getId() <= 0 || StringUtils.isEmpty(request.getOriginalPassword()) || StringUtils.isEmpty(request.getNewPassword())) {
            return Result.fail(GeneralCode.Param_Error);
        }
        if (Objects.equals(request.getOriginalPassword(), request.getNewPassword())) {
            return Result.fail(GeneralCode.Param_Error.getCode(), "新旧密码不能相同");
        }
        if (!request.getNewPassword().matches(passwordRegex)) {
            return Result.fail(GeneralCode.Param_Error.getCode(), "密码只能包含大小写字母或数字或下划线，且长度在6-18位之间");
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
