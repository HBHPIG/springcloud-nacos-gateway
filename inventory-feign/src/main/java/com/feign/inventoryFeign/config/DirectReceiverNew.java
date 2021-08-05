package com.feign.inventoryFeign.config;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 监听队列名称 TestDirectQueue
 * @author HBH
 */
@Component
@RabbitListener(queues = {"TestDirectQueue","TestDirectQueue1"})
public class DirectReceiverNew {

    @RabbitHandler
    public void process(Map message){
        if(!message.isEmpty()){
            System.out.println("第二个DirectReceiver消费者收到消息："+message.toString());
        }
    }
}
