package com.boom.success.request;

import lombok.Data;

@Data
public class ModifyPasswordRequest {
    private int id;
    private String originalPassword;
    private String newPassword;
}
