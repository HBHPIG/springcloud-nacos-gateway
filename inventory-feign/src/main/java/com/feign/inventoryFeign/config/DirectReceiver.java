package com.feign.inventoryFeign.config;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 监听队列名称 TestDirectQueue
 * @author HBH
 */
@Component
//@RabbitListener(queues = {"TestDirectQueue","TestDirectQueue1"})
public class DirectReceiver {

    @RabbitHandler
    public void process(Map message){
        if(!message.isEmpty()){
            System.out.println("第一个DirectReceiver消费者收到消息："+message.toString());
        }
    }
}
