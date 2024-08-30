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
        this.name = getName();
        this.outName = getOutName();
        this.inName = getInName();

    }


}
