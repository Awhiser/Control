package com.sisi.control.mq.mqconfig;

import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConditionalOnProperty("control.mq.enable")
public class MyRabbitAutoConfiguration extends RabbitAutoConfiguration {
}
