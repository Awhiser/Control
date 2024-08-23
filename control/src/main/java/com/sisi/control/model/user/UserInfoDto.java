package com.sisi.control.model.user;

import lombok.Data;
import org.springframework.util.StringUtils;

@Data
public class UserInfoDto {
    public String id;
    public String name;
    public String displayName;
    public String phone;
    public String mail;
    public String password;

    //todo role

    public UserInfoDto(){

    }

    public UserInfoDto(UserInfo user){
        this.id = user.getId();
        this.name = user.getName();
        this.displayName = user.getDisplayName();
        this.phone = user.getPhone();
        this.mail = user.getMail();
      //  this.password = user.getPassword();
    }


}
