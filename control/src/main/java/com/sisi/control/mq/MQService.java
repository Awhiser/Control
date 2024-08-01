package com.sisi.control.mq;

import com.sisi.control.mq.model.ControlMessage;
import com.sisi.control.mq.model.TaskMessage;
import com.sisi.control.mq.mqconfig.ExchangeConst;
import com.sisi.control.mq.mqconfig.MQConfig;
import com.sisi.control.mq.mqconfig.RoutingKeyConst;
import com.sisi.control.utils.log.LogHelper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class MQService {
    @Autowired
    ApplicationEventPublisher localPublisher;



    @Autowired
    MQConfig MQConfig;
    private RabbitTemplate rabbitTemplate;

    @Autowired
    public MQService(ApplicationEventPublisher localPublisher, MQConfig MQConfig){
          this.localPublisher = localPublisher;
          this.MQConfig = MQConfig;
          rabbitTemplate = MQConfig.rabbitTemplate();
    }

    public void publishEvent(MQType MQType, Map<String,Object> params){
        rabbitTemplate.convertAndSend(ExchangeConst.DEFAULT, RoutingKeyConst.DEFAULT, "hhh");
       // rabbitTemplate.con;
        //发送事件
        ControlMessage event = new ControlMessage();
        event.mqType = MQType;
        event.params = params;
        localPublisher.publishEvent(event);

    }

    public void publishTaskMsg(TaskMessage message){
        try {
            if(MQConfig.isEnableMQ()){
                rabbitTemplate.convertAndSend(ExchangeConst.Task, RoutingKeyConst.Task, message);
                return;
            }
            localPublisher.publishEvent(message);
        }catch (Exception e){
            LogHelper.logError("publishTaskMsg ",e);
        }

    }



}
