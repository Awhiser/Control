package com.sisi.control.service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.sisi.control.context.ContextHolder;
import com.sisi.control.context.ControlContext;
import com.sisi.control.model.user.UserConnector;
import com.sisi.control.model.user.UserInfo;
import com.sisi.control.repository.impl.UserConnectorDao;
import com.sisi.control.repository.impl.UserDao;
import com.sisi.control.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.net.http.HttpClient;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserConnectorService {

    UserConnectorDao userConnectorDao;
    HttpUtils httpUtils;
    UserDao userDao;
    @Autowired
    public UserConnectorService(UserConnectorDao userConnectorDao, HttpUtils httpUtils,  UserDao userDao){
        this.userConnectorDao = userConnectorDao;
        this.httpUtils = httpUtils;
        this.userDao = userDao;
    }


    public void save(UserConnector userConnector){
        ControlContext context = ContextHolder.getContext();
        userConnector.setIsDelete(false);
        userConnector.setTenantId(context.getTenantId());
        userConnector.setId(context.getTenantId() + UUID.randomUUID().toString());
        userConnectorDao.save(userConnector);
    }

    public List<UserConnector> getUserConnectors(){
       return  userConnectorDao.findAll();
    }

    public void update(UserConnector userConnector){
        userConnectorDao.save(userConnector);
    }


    public void syncUser(String id){
        var userConnector = userConnectorDao.findById(id);
        var usersJson = httpUtils.get(userConnector.getUrl());

        var userArray = new Gson().fromJson(usersJson, JsonArray.class);

        List<UserInfo> userInfoList = new ArrayList<>();
        ControlContext context = ContextHolder.getContext();
        for(int i = 0; i < userArray.size(); i++) {
            var userElement= userArray.get(i).getAsJsonObject();
            UserInfo userInfo = new UserInfo();
            userInfo.setIsDelete(false);
            userInfo.setTenantId(context.getTenantId());
            userInfo.setId(userElement.get(userConnector.getIdField()).getAsString());
            userInfo.setName( userElement.get(userConnector.getNameField()).getAsString());
            userInfo.setDisplayName(userInfo.getName());
            userInfo.setPassword(userElement.get(userConnector.getPasswordField()).getAsString());
            if(StringUtils.hasText(userConnector.getDisplayNameField())){
                userInfo.setDisplayName(userElement.get(userConnector.getDisplayNameField()).getAsString());
            }
            if(StringUtils.hasText(userConnector.getMailField())){
                userInfo.setPassword(userElement.get(userConnector.getMailField()).getAsString());
            }
            userInfoList.add(userInfo);
        }
        userDao.saveAll(userInfoList);
    }


    @Transactional(readOnly = false)
    public void delete(String id){
        userConnectorDao.deleteByIdWithRealRemove(id);
    }

}
