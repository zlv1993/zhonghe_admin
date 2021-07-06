package com.admin.zhonghe.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMqQueue {
    @Bean
    public Queue queue(){
        return new Queue("queue");
    }
}
