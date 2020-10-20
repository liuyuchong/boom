package com.boom.success.response;

import lombok.Data;

import java.util.List;

@Data
public class UserInfo {
    private int total;
    private List<UserResponse> users;
}
