package com.sisi.control.model.tasklink;

public class TaskLinkDto {
    public String id;
    public TaskLinkEnum linkType;
    public String linkName;
    public String description;
    public String url;
    public String taskId;

    public TaskLinkDto() {

    }

    public TaskLinkDto(TaskLink link) {
        this.linkType = link.linkType;
        this.linkName = link.linkName;
        this.description = link.description;
        this.url = link.url;
        this.taskId = link.taskId;
        this.id = link.id;

    }


}
