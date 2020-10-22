package com.boom.success.request;

import com.boom.success.consts.RoleEnums;
import lombok.Data;

@Data
public class AddUserRequest {
    //用户名
    private String username;
    //队伍信息
    private String group;
    //职位
    private String position;
    //手机号
    private String phoneNumber;
    /**
     * @see RoleEnums
     * ADMIN(1,"系统管理员"),
     * WORD(2,"读写权限"),
     * GUEST(3,"只读权限");
     */
    private Integer role;
}
