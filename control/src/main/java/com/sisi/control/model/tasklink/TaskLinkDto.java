package com.sisi.control.model.tasklink;

public class TaskLinkDto {
    public String id;
    public String outTaskId;
    public String linkId;
    public String linkName;
    public String inTaskId;
    public boolean isOut;

    public TaskLinkDto() {

    }

    public TaskLinkDto(TaskLink link) {
        this.linkId = link.linkId;
        this.isOut = link.isOut;
        this.outTaskId = link.outTaskId;
        this.inTaskId = link.inTaskId;
        this.id = link.id;

    }


}
