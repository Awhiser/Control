package com.sisi.control.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.sisi.control.model.task.Task;
import com.sisi.control.model.workflow.WorkFlowConfig;
import com.sisi.control.utils.HttpUtils;
import okhttp3.Request;
import org.springframework.beans.factory.annotation.Autowired;

public class ExtWorkFlowService {

    @Autowired
    HttpUtils httpUtils;

    public void  execute(){

    }

    public void  create(Task task){

        task.getType();
        WorkFlowConfig workFlowConfig = new WorkFlowConfig();
        var repStr = httpUtils.get(workFlowConfig.getCreateUrl());

        var rep = new Gson().fromJson(repStr, JsonObject.class);




    }

}
