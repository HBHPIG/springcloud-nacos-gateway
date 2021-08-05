package com.feign.inventoryFeign.controller;

import com.feign.inventoryFeign.config.R;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author HBH
 */
@RestController
@RequestMapping("/feign")
public class RabbitController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @GetMapping("/sendMessageFromConsumerToRabbit")
    public R sendMessageFromConsumerToRabbit(){
        Map<String, Object> map = new HashMap();
        map.put("username", UUID.randomUUID());
        map.put("usercode", "usercode"+UUID.randomUUID());
        map.put("remark", "remark"+UUID.fromString(UUID.randomUUID().toString()));
        rabbitTemplate.convertAndSend("TestDirectExchange1", "TestDirectRouting1", map);
        return R.ok().put("sendMessageFromConsumerToRabbit", map);
    }
}
