package com.sisi.control.service;

import com.sisi.control.context.ContextHolder;
import com.sisi.control.model.PageResult;
import com.sisi.control.model.controlenum.MessageEnum;
import com.sisi.control.model.response.Response;
import com.sisi.control.model.user.UserInfo;
import com.sisi.control.model.user.UserLoginResult;
import com.sisi.control.model.user.UserSearchParam;
import com.sisi.control.model.user.UserInfoDto;
import com.sisi.control.repository.impl.UserDao;
import com.sisi.control.utils.CommonUtils;
import com.sisi.control.utils.token.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private UserDao userDao;
    private TokenUtil tokenUtil;

    @Autowired
    public UserService(UserDao userDao, TokenUtil tokenUtil) {
        this.userDao = userDao;
        this.tokenUtil = tokenUtil;
    }

    public Response register(UserInfo userInfo) {
        if (userDao.getUserByUserName(userInfo.name) != null) {
            return Response.fail(MessageEnum.UserNameExist);
        }
        userInfo.id = CommonUtils.idGenerate();
        if(!StringUtils.hasText(userInfo.getDisplayName())) {
            userInfo.setDisplayName(userInfo.getName());
        }
        userDao.save(userInfo);
        return Response.success();
    }

    public Response login(String userName, String password) {
        //todo 插拔
        var user = userDao.loginUserByUserNameAndPassword(userName,password);
        if (user == null) {
            return Response.fail(MessageEnum.UserNamePasswordError);
        }
//        if (!user.getPassword().equals(password)) {
//            return Response.fail(MessageEnum.PasswordError);
//        }
        //generate token
        var token = tokenUtil.getToken(user.id, ContextHolder.getContext().getTenantId());

        //储存token
        tokenUtil.saveToken(token);

        UserLoginResult userLoginResult = new UserLoginResult();
        userLoginResult.userInfo = user;
        userLoginResult.token = token;
        //返回登录信息  token userId
        return Response.success(userLoginResult);
    }

    public String getLoginCheckWay() {

        return "";
    }

    public UserInfoDto getUserById(String id) {
        var user = userDao.getById(id);
        // UserVo vo = new UserVo(user);
        return user;
    }

    public List<UserInfoDto> getUserByIds(List<String> ids) {
        return userDao.getByIds(ids);

    }

    public PageResult<UserInfoDto> getUserList(UserSearchParam param) {
        var pageRes = userDao.getUserList(param);
        return pageRes;
    }

    //todo 全传
    public void update(UserInfo userInfo){
//        var user = userDao.findById(userInfo.getId());
//
//        if(StringUtils.hasText(userInfo.getDisplayName()) ){
//            user.setDisplayName(userInfo.getDisplayName());
//        }
//
//        if( StringUtils.hasText(userInfo.getMail()) ) {
//            user.setMail(userInfo.getMail());
//        }
        userDao.save(userInfo);
    }

    public void delete(String id) {
        userDao.deleteById(id);
    }

}
