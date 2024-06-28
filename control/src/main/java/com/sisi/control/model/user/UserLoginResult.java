package com.sisi.control.model.user;

import lombok.Data;

@Data
public class UserLoginResult {
    public String token;
    public UserInfo userInfo;
}
