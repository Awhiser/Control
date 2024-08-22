package com.sisi.control.mq.consumer;

import com.sisi.control.context.ContextHolder;
import com.sisi.control.mq.MQType;
import com.sisi.control.mq.model.TaskMessage;
import com.sisi.control.mq.mqconfig.QueueConst;
import com.sisi.control.service.task.TaskService;
import com.sisi.control.utils.log.LogHelper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class TaskMQConsumer {

    private TaskService taskService;

    @Autowired
    TaskMQConsumer(TaskService taskService){
        this.taskService = taskService;
    }

    @RabbitListener(queues = QueueConst.Task)
    @EventListener
    @Async()
    public void consumer(TaskMessage message) {
        //重新设置上下文,Warn 线程池会上下文共享
        ContextHolder.setContext(message.getContext());
        LogHelper.logInfo("处理TaskUpdate消息: "+message);
        if(message.mqType == MQType.TaskUpdate) {
            taskService.updateChangeLog(message.getTask(),message.getOldTask(),message.getContext().getToken().getUserId());
        }
    }
}
