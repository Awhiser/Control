package com.sisi.control.model.tasklink;

import com.sisi.control.model.task.TaskDto;
import lombok.Data;

@Data
public class TaskLinkDto {
    public String id;
    public TaskDto outTask;
    public TaskLinkTypeDto link;
    public TaskDto inTask;


    public TaskLinkDto() {

    }

    public TaskLinkDto(TaskLink taskLink) {
        this.outTask = new TaskDto();
        this.outTask.setId(taskLink.getOutTaskId());
        this.inTask = new TaskDto();
        this.inTask.setId(taskLink.getInTaskId());
        this.link = new TaskLinkTypeDto();
        this.link.setId(taskLink.getLinkId());
        this.id = link.id;
    }


}
