package com.boom.success.response;

import lombok.Data;

@Data
public class UserResponse {
    private Integer id;
    private String username;
    private String group;
    private String position;
    private String phoneNumber;
    private Integer role;
}
