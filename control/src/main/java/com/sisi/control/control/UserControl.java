package com.sisi.control.control;

import com.sisi.control.model.response.Response;
import com.sisi.control.model.user.UserInfo;
import com.sisi.control.model.user.UserLoginParam;
import com.sisi.control.model.user.UserLoginResult;
import com.sisi.control.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserControl {

    private UserService userService;

    UserControl(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/login")
    public Response<UserLoginResult> login(@RequestBody UserLoginParam userLoginParam){
        return userService.login(userLoginParam.getName(),userLoginParam.getPassword());
    }

    @PostMapping("/register")
    public Response register(@RequestBody UserInfo userInfo){
        return userService.register(userInfo);
    }

}
