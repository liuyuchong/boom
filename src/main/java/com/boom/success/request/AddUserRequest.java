package com.boom.success.request;

import com.boom.success.consts.RoleEnums;
import lombok.Data;

@Data
public class AddUserRequest {
    private String username;
    private String group;
    private String position;
    private String phoneNumber;
    /**
     * @see RoleEnums
     */
    private Integer role;
}
