package com.my.inventory.common.utils;

import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author HBH
 */
@Configuration
public class RabbitConfig {

    @Bean
    public RabbitTemplate createRabbitTemplate(ConnectionFactory connectionFactory){

        RabbitTemplate rabbitTemplate = new RabbitTemplate();

        rabbitTemplate.setConnectionFactory(connectionFactory);

        // 开启mandatory,触发回调函数，无论消息推送怎么样强制回调
        rabbitTemplate.setMandatory(true);




//        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
//            @Override
//            public void confirm (CorrelationData correlationData, boolean ack, String cause) {
//                System.out.println("ConfirmCallback:  相关数据："+correlationData);
//                System.out.println("ConfirmCallback:  确认情况："+ack);
//                System.out.println("ConfirmCallback:  原因："+cause);
//            }
//        });
//
//
//
//        rabbitTemplate.setReturnsCallback(new RabbitTemplate.ReturnsCallback() {
//            @Override
//            public void returnedMessage (ReturnedMessage returned) {
//                System.out.println("ReturnCallback:     "+"消息："+returned.getMessage());
//                System.out.println("ReturnCallback:     "+"回应码："+returned.getReplyCode());
//                System.out.println("ReturnCallback:     "+"回应信息："+returned.getReplyText());
//                System.out.println("ReturnCallback:     "+"交换机："+returned.getExchange());
//                System.out.println("ReturnCallback:     "+"路由键："+returned.getRoutingKey());
//            }
//        });

        rabbitTemplate.setConfirmCallback((correlationData, b, s) -> {
//            @Override
//            public void confirm (CorrelationData correlationData, boolean b, String s) {
            System.out.println("ConfirmCallback:  相关数据："+correlationData);
            System.out.println("ConfirmCallback:  确认情况："+b);
            System.out.println("ConfirmCallback:  原因："+s);
//            }
        });

        rabbitTemplate.setReturnsCallback((returned) ->{
            System.out.println("ReturnCallback:     "+"消息："+returned.getMessage());
            System.out.println("ReturnCallback:     "+"回应码："+returned.getReplyCode());
            System.out.println("ReturnCallback:     "+"回应信息："+returned.getReplyText());
            System.out.println("ReturnCallback:     "+"交换机："+returned.getExchange());
            System.out.println("ReturnCallback:     "+"路由键："+returned.getRoutingKey());
        });
        return rabbitTemplate;

    }
}
