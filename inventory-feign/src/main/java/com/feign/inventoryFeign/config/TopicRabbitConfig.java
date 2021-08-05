package com.feign.inventoryFeign.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Topic Exchange 主题交换机
 * @author HBH
 */
@Configuration
public class TopicRabbitConfig {

    private final static String  man = "topic.man";

    private final static String  woman = "topic.woman";

    @Bean
    public Queue FistQueue(){

        return new Queue(TopicRabbitConfig.man ,true);
    }

    @Bean
    public Queue SecondQueue(){
        return new Queue(TopicRabbitConfig.woman, true);
    }

    @Bean
    TopicExchange exchange(){
        return new TopicExchange("topicExchange");
    }

    /**
     * 队列1绑定主题交换机，绑定键值为Topic.man,这样子消息携带的路由键值为Topic.man,才会转发到该队列
     * @return
     */
    @Bean
    Binding bindingExchangeMessage(){

        return BindingBuilder.bind(FistQueue()).to(exchange()).with("topic.man");
    }

    /**
     * SecondQueue队列绑定topicExchange,键值为通配路由规则Topic.#，满足条件转发的该队列，测试提交git
     * 路由键为通配规则
     * @return
     */
    @Bean
    Binding bindingExchangeMessage2(){

        return BindingBuilder.bind(SecondQueue()).to(exchange()).with("topic.#");
    }
}
