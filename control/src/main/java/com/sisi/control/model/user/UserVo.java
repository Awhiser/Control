package com.sisi.control.model.user;

import lombok.Data;
import org.springframework.util.StringUtils;

@Data
public class UserVo {
    public String id;
    public String name;
    public String displayName;
    public String phone;
    public String mail;
    public String password;

    //todo role

    public UserVo(){

    }



}
