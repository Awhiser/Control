package com.sisi.control.model.task;

import com.sisi.control.model.user.UserInfoDto;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class TaskDto {
    public String id;
    public String title;
    public UserInfoDto assignee;
    public String type;
  // todo public Version versionId;
    public String priority;
    public List<String> tags;
    public String description;
    public Date duedate;
    public String projectId;
    public UserInfoDto creator;
    public Date createTime;
    public Date updateTime;

    public TaskDto(){

    }



    public TaskDto(Task param){
        this.id = param.getId();
        this.title = param.getTitle();
        this.type = param.getType();
        this.assignee = new UserInfoDto();
        this.assignee.id = param.assignee;
        this.creator = new UserInfoDto();
        this.creator.id = param.creator;
        //this.versionId = param.versionId;
        this.priority = param.getPriority();
        this.tags = param.getTags();
        this.description = param.getDescription();
        this.duedate = param.getDuedate();
        this.projectId = param.getProjectId();
        this.createTime = param.getCreateTime();
        this.updateTime = param.getUpdateTime();
    }




}
