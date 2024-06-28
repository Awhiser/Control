package com.sisi.control.model.task;

import com.sisi.control.model.user.UserInfo;
import com.sisi.control.model.user.UserVo;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Data
public class TaskVo {
    public String id;
    public String title;
    public UserVo assignee;
    public String type;
  // todo public Version versionId;
    public String priority;
    public List<String> tags;
    public String description;
    public Date duedate;
    public String projectId;

    public Date createTime;
    public Date updateTime;

    public TaskVo(){

    }

    public TaskVo(Task param , UserInfo userInfo){
        this.id = param.getId();
        this.title = param.getTitle();
        this.type = param.getType();
        this.assignee = generateAssignee(userInfo);
        //this.versionId = param.versionId;
        this.priority = param.getPriority();
        this.tags = param.getTags();
        this.description = param.getDescription();
        this.duedate = param.getDuedate();
        this.projectId = param.getProjectId();
        this.createTime = param.getCreateTime();
        this.updateTime = param.getUpdateTime();
    }

    private UserVo generateAssignee(UserInfo userInfo){
        if(userInfo == null || !StringUtils.hasText(userInfo.getId())){
            return null;
        }
        UserVo assigneeVo = new UserVo();
        assigneeVo.setId(userInfo.getId());
        userInfo.setName(userInfo.getName());
        assigneeVo.setDisplayName(assigneeVo.getDisplayName());
        return assigneeVo;
    }



}
