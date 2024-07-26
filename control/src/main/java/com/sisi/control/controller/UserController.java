package com.sisi.control.controller;

import com.sisi.control.model.PageView;
import com.sisi.control.model.response.Response;
import com.sisi.control.model.user.*;
import com.sisi.control.service.UserConnectorService;
import com.sisi.control.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserService userService;

    private UserConnectorService userConnectorService;

    UserController(UserService userService, UserConnectorService userConnectorService)
    {
        this.userService = userService;
        this.userConnectorService = userConnectorService;
    }

    @PostMapping("/login")
    public Response<UserLoginResult> login(@RequestBody UserLoginParam userLoginParam){
        return userService.login(userLoginParam.getName(),userLoginParam.getPassword());
    }

    @PostMapping("/register")
    public Response register(@RequestBody UserInfo userInfo){
        return userService.register(userInfo);
    }

    @PostMapping("/getList")
    public Response<PageView<UserVo>> getUserList(@RequestBody UserSearchParam searchParam){
        var res = userService.getUserList(searchParam);
        return Response.success(res);
    }

    @PostMapping("/update")
    public Response updateSimpleInfo(@RequestBody UserInfo userInfo) {
        userService.update(userInfo);
        return Response.success();
    }

    @GetMapping("/delete")
    public Response delete(String id) {
        userService.delete(id);
        return Response.success();
    }

    @PostMapping("/saveUserConnector")
    public Response saveUserConnector(@RequestBody UserConnector userConnector) {
        userConnectorService.save(userConnector);
        return Response.success();
    }

    @PostMapping("/updateUserConnector")
    public Response updateUserConnector(@RequestBody UserConnector userConnector) {
        userConnectorService.update(userConnector);
        return Response.success();
    }

    @GetMapping("/getUserConnectors")
    public Response<UserConnector> getUserConnectors() {
        var res = userConnectorService.getUserConnectors();
        return Response.success(res);
    }
    //todo 定时拉取
    @GetMapping("/syncUser")
    public Response syncUser(String id) {
        userConnectorService.syncUser(id);
        return Response.success();
    }

    @GetMapping("/syncUserConnector")
    public Response syncUserConnector(@RequestParam  String id){
        userConnectorService.syncUser(id);
        return Response.success();
    }


    @GetMapping("/deleteUserConnector")
    public Response deleteUserConnector(@RequestParam  String id){
        userConnectorService.delete(id);
        return Response.success();
    }









}
