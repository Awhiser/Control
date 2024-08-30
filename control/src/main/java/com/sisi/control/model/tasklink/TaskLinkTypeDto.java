package com.sisi.control.model.tasklink;

import lombok.Data;

@Data
public class TaskLinkTypeDto {
    public String id;
    public String name;
    public String outName;
    public String inName;

    public TaskLinkTypeDto(){

    }

    public TaskLinkTypeDto(TaskLinkType type){
        this.id = type.getId();
        this.name = type.getName();
        this.outName = type.getOutName();
        this.inName = type.getInName();

    }


}
