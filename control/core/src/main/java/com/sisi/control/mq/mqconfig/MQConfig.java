package com.sisi.control.mq.mqconfig;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class MQConfig {


    /**
     * rabbitMQ服务器的地址
     */
    @Value("${control.mq.addresses:192.168.56.105:5672}")
    private String addresses;
    /**
     * rabbitMQ用户名
     */
    @Value("${control.mq.username:root}")
    private String username;
    /**
     * rabbitMQ密码
     */
    @Value("${control.mq.password:root}")
    private String password;

    @Value("${control.mq.enable:false}")
    private boolean enableMq;
    /**
     * 注册rabbitMQ的Connection
     *
     * @return
     */
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
        cachingConnectionFactory.setAddresses(this.addresses);
        cachingConnectionFactory.setUsername(this.username);
        cachingConnectionFactory.setPassword(this.password);
        return cachingConnectionFactory;
    }

    /**
     * 注册rabbitAdmin 方便管理
     *
     * @param connectionFactory
     * @return
     */
    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return   new RabbitAdmin(connectionFactory);
    }

    @Bean(name = "rabbitTemplate")
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(this.connectionFactory());
        template.setMessageConverter(new Jackson2JsonMessageConverter());
        return template;
    }

    public Boolean isEnableMQ(){
        return this.enableMq;
    }



}
