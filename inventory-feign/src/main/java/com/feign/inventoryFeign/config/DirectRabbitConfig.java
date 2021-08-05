package com.feign.inventoryFeign.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author HBH
 */
@Configuration
public class DirectRabbitConfig {

    /**
     * 队列 TestDirectQueue
     * @return
     */
    @Bean
    public Queue TestDirectQueue(){

        return new Queue("TestDirectQueue1", true);
    }

    /**
     * Direct交换机 TestDirectExchange
     * @return
     */
    @Bean
    DirectExchange TestDirectExchange(){

        return new DirectExchange("TestDirectExchange1");
    }


    /**
     * @return
     */
    @Bean
    Binding bindingDirect(){
        return BindingBuilder.bind(TestDirectQueue()).to(TestDirectExchange()).with("TestDirectRouting1");
    }
}
