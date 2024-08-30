package com.sisi.control.model.tasklink;

public class TaskLinkDto {
    public String id;
    public String taskId;
    public String linkId;
    public String linkName;
    public String linkTaskId;
    public boolean isOut;

    public TaskLinkDto() {

    }

    public TaskLinkDto(TaskLink link) {
        this.linkId = link.linkId;
        this.isOut = link.isOut;
        this.linkTaskId = link.linkTaskId;
        this.taskId = link.taskId;
        this.id = link.id;

    }


}
