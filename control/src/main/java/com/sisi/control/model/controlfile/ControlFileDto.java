package com.sisi.control.model.controlfile;

import java.util.Date;

public class ControlFileDto {
    public String id;
    public String name;
    public String url;
    public String taskId;
    public Date createTime;

    public ControlFileDto(ControlFile file){
        this.id = file.id;
        this.name = file.name;
        this.url = file.url;
        this.taskId = file.taskId;
        this.createTime = file.createTime;
    }

}
