package com.feign.inventoryFeign.config;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author HBH
 */
@Configuration
public class MessageListenerConfig {

    @Autowired
    private CachingConnectionFactory cachingConnectionFactory;

    @Autowired
    private AckReceiver ackReceiver;

    @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer(){

        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(cachingConnectionFactory);
        container.setConcurrentConsumers(1);
        container.setMaxConcurrentConsumers(1);
        // 默认自动确认，改为手动
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);

        container.setQueueNames("TestDirectQueue", "TestDirectQueue1", "fanout.A", "fanout.B",
                "fanout.C","Topic.man", "Topic.woman");
        container.setMessageListener(ackReceiver);

        return container;
    }

}
