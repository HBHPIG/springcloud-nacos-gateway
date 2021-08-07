package com.feign.inventoryFeign.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author HBH
 */
@Configuration
public class FanoutRabbitConfig {

    /**
     * 创建3个队列，fanout.A、fanout.B、fanout.C
     * 各自绑定到扇形交换机fanoutExchange
     * 扇形交换机无需配置路由键，设置了也无效
     */

    @Bean
    public Queue queueA(){
        return new Queue("fanout.A");
    }

    @Bean
    public Queue queueB(){
        return new Queue("fanout.B");
    }

    @Bean
    public Queue queueC(){
        return new Queue("fanout.C");
    }

    @Bean
    FanoutExchange fanoutExchange(){
        return new FanoutExchange("fanoutExchange");
    }

    @Bean
    Binding bindingA(){
        return BindingBuilder.bind(queueA()).to(fanoutExchange());
    }

    @Bean
    Binding bindingB(){
        return BindingBuilder.bind(queueB()).to(fanoutExchange());
    }

    @Bean
    Binding bindingC(){
        return BindingBuilder.bind(queueC()).to(fanoutExchange());
    }
}
