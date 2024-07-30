package com.sisi.control.service;

import com.sisi.control.context.ContextHolder;
import com.sisi.control.model.PageView;
import com.sisi.control.model.controlenum.MessageEnum;
import com.sisi.control.model.response.Response;
import com.sisi.control.model.user.UserInfo;
import com.sisi.control.model.user.UserLoginResult;
import com.sisi.control.model.user.UserSearchParam;
import com.sisi.control.model.user.UserVo;
import com.sisi.control.repository.impl.UserDao;
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
        var tenantId = ContextHolder.getContext().tenantId;
        userInfo.id = tenantId + userInfo.name;
        userInfo.isDelete = false;
        userInfo.tenantId = tenantId;
        userDao.save(userInfo);
        return Response.success();
    }

    public Response login(String userName, String password) {
        //todo 插拔
        var user = userDao.getUserByUserName(userName);
        if (user == null) {
            return Response.fail(MessageEnum.UserNameNotExist);
        }
        if (!user.getPassword().equals(password)) {
            return Response.fail(MessageEnum.PasswordError);
        }
        //generate token
        var token = tokenUtil.getToken(user.id, user.tenantId);

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

    public UserInfo getUserById(String id) {
        var user = userDao.findById(id);
        // UserVo vo = new UserVo(user);
        return user;
    }

    public List<UserInfo> getUserByIds(List<String> ids) {
        return userDao.findByIds(ids);

    }

    public PageView<UserVo> getUserList(UserSearchParam param) {
        var pageRes = userDao.getUserList(param);
        List<UserVo> dataList = new ArrayList<>();
        pageRes.stream().forEach(user -> {
            dataList.add(new UserVo(user));
        });
        PageView<UserVo> userVoPageView = new PageView(pageRes);
        userVoPageView.setDataList(dataList);
        return userVoPageView;
    }

    public void update(UserInfo userInfo){
        var user = userDao.findById(userInfo.getId());

        if(StringUtils.hasText(userInfo.getDisplayName()) ){
            user.setDisplayName(userInfo.getDisplayName());
        }

        if( StringUtils.hasText(userInfo.getMail()) ) {
            user.setMail(userInfo.getMail());
        }
        userDao.save(user);
    }

    public void delete(String id) {
        userDao.deleteById(id);
    }

}
