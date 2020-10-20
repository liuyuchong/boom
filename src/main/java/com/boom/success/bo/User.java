package com.boom.success.bo;

import com.boom.success.consts.RoleEnums;
import lombok.Data;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("user")
@Data
public class User {

    @Id
    private Integer id;

    //用户名
    @Column
    private String username;

    //密码
    @Column
    private String password;

    //队伍信息
    @Column("group_info")
    private String group;

    //职位信息
    @Column
    private String position;

    //手机号
    @Column("phone_number")
    private String phoneNumber;

    /**
     * @see RoleEnums
     */
    @Column
    private Integer role;
}
