package com.sisi.control.model.task;

import com.sisi.control.model.user.UserInfo;
import com.sisi.control.model.user.UserInfoDto;
import com.sisi.control.model.version.Version;
import com.sisi.control.model.version.VersionDto;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class TaskDto {
    public String id;
    public String title;
    public UserInfoDto assignee;
    public String type;
    public VersionDto version;
    public String priority;
    public List<String> tags;
    public String description;
    public Date duedate;
    public String projectId;
    public String status;
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
        this.version = new VersionDto();
        this.version.setId(param.versionId);
        this.priority = param.getPriority();
        this.tags = param.getTags();
        this.description = param.getDescription();
        this.duedate = param.getDuedate();
        this.projectId = param.getProjectId();
        this.status = param.getStatus();
        this.createTime = param.getCreateTime();
        this.updateTime = param.getUpdateTime();
    }




}
