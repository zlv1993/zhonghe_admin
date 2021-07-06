package com.admin.zhonghe.config;


import com.admin.zhonghe.domain.configuration.RabbitMsg;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component

public class RabbitReceive {
    @RabbitListener(queues = "queue")
    public void send(RabbitMsg rabbitMsg){
         System.out.println(rabbitMsg.getMsg());
    }
}
