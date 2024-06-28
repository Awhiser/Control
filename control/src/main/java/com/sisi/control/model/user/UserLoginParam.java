package com.sisi.control.model.user;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

@Data
public class UserLoginParam {
    public String name;
    public String password;
}
