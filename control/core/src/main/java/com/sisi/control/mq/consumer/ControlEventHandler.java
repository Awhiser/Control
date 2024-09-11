package com.sisi.control.mq.consumer;

import com.sisi.control.mq.model.ControlMessage;
import org.springframework.context.event.EventListener;

public class ControlEventHandler {


    @EventListener
    public void handle(ControlMessage controlMessage){

    }

//    @RabbitListener(queues = QueueEnum.DEFAULT.getName())
//    public void receive_email(Object msg, Message message, Channel channel){
//        System.out.println("QUEUE_INFORM_EMAIL msg"+msg);
//    }


}
