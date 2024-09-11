package com.sisi.control.mq.model;

import com.sisi.control.context.ContextHolder;
import com.sisi.control.model.task.TaskDto;
import com.sisi.control.mq.MQType;
import lombok.Data;

@Data
public class TaskMessage extends ControlMessage{
    public TaskDto task;
    public TaskDto oldTask;

    public TaskMessage(){

    }

    public TaskMessage(MQType mqType, TaskDto task) {
        this.mqType = mqType;
        this.task = task;
        this.context = ContextHolder.getContext();
    }

    public TaskMessage(MQType mqType, TaskDto task, TaskDto oldTask) {
        this.mqType = mqType;
        this.task = task;
        this.oldTask = oldTask;
        this.context =  ContextHolder.getContext();
    }

}
