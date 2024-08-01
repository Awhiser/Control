package com.sisi.control.mq.mqconfig;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig {
    @Bean
    public DirectExchange defaultExchange() {
        return new DirectExchange(ExchangeConst.DEFAULT);
    }

    @Bean
    public Queue defaultMsgQueue() {
        return new Queue(QueueConst.DEFAULT, true);
    }

    @Bean
    public Binding defaultMsgQueueBinding() {
        return BindingBuilder
                .bind(defaultMsgQueue())
                .to(defaultExchange())
                .with(RoutingKeyConst.DEFAULT);
    }



    @Bean
    public DirectExchange taskExchange() {
        return new DirectExchange(ExchangeConst.Task);
    }

    @Bean
    public Queue taskMsgQueue() {
        return new Queue(QueueConst.Task, true);
    }

    @Bean
    public Binding taskMsgQueueBinding() {
        return BindingBuilder
                .bind(taskMsgQueue())
                .to(taskExchange())
                .with(RoutingKeyConst.Task);
    }


}
