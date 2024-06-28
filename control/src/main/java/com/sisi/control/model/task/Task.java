package com.sisi.control.model.task;

import com.sisi.control.model.AbstractEntity;
import jakarta.persistence.Entity;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Task extends AbstractEntity {
    public String title;
    public String assignee;
    public String type;
 //   public String versionId;
    public String priority;
    public String tags;
    public String description;
    public Date  duedate;
    public String projectId;
    public String parentId;
    public Date createTime;
    public Date updateTime;

    public Task(){

    }

    public Task(TaskCreateParam param){
        this.title = param.getTitle();
        this.assignee = param.getAssignee();
        this.type = param.getType();
      //  this.versionId = param.versionId;
        this.priority = param.getPriority();
        this.tags = String.join(",",param.getTags()) ;
        this.description = param.getDescription();
        this.duedate = param.getDuedate();
        this.projectId = param.getProjectId();
        this.parentId = param.getParentId();
        var now = new Date();
        this.createTime = now;
        this.updateTime = now;
        this.isDelete = false;
    }

    public void setTags(List<String> tags){
        this.tags = String.join(",",tags);
    }

    public List<String> getTags(){
        if(!StringUtils.hasText(this.tags)){
            return new ArrayList<>();
        }
        return Arrays.stream(tags.split(",")).toList();
    }


}
