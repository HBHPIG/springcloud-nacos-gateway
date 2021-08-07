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
//@RabbitListener(queues = {"fanout.A","fanout.B","fanout.C"})
public class FanoutReceiver {

    @RabbitHandler
    public void process(Map message){

        System.out.println("FanoutReceiver消费者收到消息："+message.toString());

    }
}
