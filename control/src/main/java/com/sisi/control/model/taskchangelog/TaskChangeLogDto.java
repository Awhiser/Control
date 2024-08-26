package com.sisi.control.model.taskchangelog;

import com.sisi.control.model.user.UserInfoDto;
import lombok.Data;

import java.util.Date;
@Data
public class TaskChangeLogDto {
    public String id;
    public String taskId;
    public UserInfoDto operator;
    public String name;
    public String fromValue;
    public String toValue;
    public Date operateTime;

    public TaskChangeLogDto(){

    }

    public TaskChangeLogDto(TaskChangeLog log) {
        this.id = log.id;
        this.taskId = log.taskId;
        this.operator = new UserInfoDto();
        this.operator.setId(log.operator);
        this.name = log.name;
        this.fromValue = log.fromValue;
        this.toValue = log.toValue;
        this.operateTime = log.operateTime;
    }
}
