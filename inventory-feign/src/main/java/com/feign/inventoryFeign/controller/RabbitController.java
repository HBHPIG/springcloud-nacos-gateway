package com.feign.inventoryFeign.controller;

import com.feign.inventoryFeign.config.R;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    @GetMapping("/sendTopicMessage1")
    public String sendTopicMessage1() {

        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "message: M A N ";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> manMap = new HashMap<>();
        manMap.put("messageId", messageId);
        manMap.put("messageData", messageData);
        manMap.put("createTime", createTime);
//        manMap.put("topic.man","topic.man");
        rabbitTemplate.convertAndSend("topicExchange", "topic.man", manMap);
        return "ok";
    }

    @GetMapping("/sendTopicMessage2")
    public String sendTopicMessage2() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "message: woman is all ";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> womanMap = new HashMap<>();
        womanMap.put("messageId", messageId);
        womanMap.put("messageData", messageData);
        womanMap.put("createTime", createTime);
//        womanMap.put("topic.woman","topic.woman");
        rabbitTemplate.convertAndSend("topicExchange", "topic.woman", womanMap);
        return "ok";
    }

    @GetMapping("/sendFanoutMessage")
    public String sendFanoutMessage() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "message: woman is all ";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> womanMap = new HashMap<>();
        womanMap.put("messageId", messageId);
        womanMap.put("messageData", messageData);
        womanMap.put("createTime", createTime);
//        womanMap.put("topic.woman","topic.woman");
        rabbitTemplate.convertAndSend("fanoutExchange", null, womanMap);
        return "ok";
    }
}
