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
@RabbitListener(queues = {"topic.man"})
public class TopicReceiver {

    @RabbitHandler
    public void process(Map message){
        System.out.println("TopicManReceiver消费者收到消息："+message.toString());
        /*if(!message.isEmpty() && "Topic.man".equals(message.get("Topic.man"))){
            System.out.println("TopicManReceiver消费者收到消息："+message.toString());
        }

        if(!message.isEmpty() && "Topic.woman".equals(message.get("Topic.woman"))){
            System.out.println("TopicTotalReceiver消费者收到消息："+message.toString());
        }*/
    }
}
