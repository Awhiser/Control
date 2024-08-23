package com.sisi.control.model.tasktype;

public class TaskTypeDto {
    public String id;
    public String name;
    public String img;

    public TaskTypeDto(TaskType type) {
        this.id = type.id;
        this.name = type.name;
        this.img = type.img;
    }

}
