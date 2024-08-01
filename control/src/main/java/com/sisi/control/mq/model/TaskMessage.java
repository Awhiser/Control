package com.sisi.control.mq.model;

import com.sisi.control.context.ContextHolder;
import com.sisi.control.context.ControlContext;
import com.sisi.control.model.task.Task;
import com.sisi.control.mq.MQType;
import lombok.Data;

@Data
public class TaskMessage extends ControlMessage{
    public Task task;
    public Task oldTask;

    public TaskMessage(){

    }

    public TaskMessage(MQType mqType, Task task) {
        this.mqType = mqType;
        this.task = task;
        this.context = ContextHolder.getContext();
    }

    public TaskMessage(MQType mqType, Task task, Task oldTask) {
        this.mqType = mqType;
        this.task = task;
        this.oldTask = oldTask;
        this.context =  ContextHolder.getContext();
    }

}
