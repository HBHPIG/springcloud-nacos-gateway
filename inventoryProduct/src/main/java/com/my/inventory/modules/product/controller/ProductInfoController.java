package com.my.inventory.modules.product.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.my.inventory.common.utils.R;
import com.my.inventory.common.utils.RedisService;
import com.my.inventory.common.utils.RedisUtil;
import com.my.inventory.modules.product.entity.ProductInfo;
import com.my.inventory.modules.product.service.ProductInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author BH.huang
 * @since 2021-07-08
 */
@RestController
@Api(tags = "商品信息")
@RequestMapping("/productInfo")
public class ProductInfoController {

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private RedisService redisService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private Redisson redisson;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/query")
    @ApiOperation("查询商品")
    public R query(){
        List<ProductInfo> productInfoList = productInfoService.selectList(new EntityWrapper<ProductInfo>());
        redisUtil.set("productInfoList", productInfoList);
        stringRedisTemplate.opsForValue().set("test1122", "123");
        stringRedisTemplate.delete("test11");
        return R.ok().put( "productInfoList", redisUtil.get("productInfoList"));
    }

    @PostMapping("/reductInventory")
    public R reductInventory(){

        String code = "success";
        String lockKey = "product_101";
        /*boolean result = stringRedisTemplate.opsForValue().setIfAbsent("lockKey", lockKey);

        if(!result){
            code= "error";
        }*/
        RLock rLock = redisson.getLock(lockKey);
        rLock.lock();
        try {
            Integer stocks = Integer.parseInt(stringRedisTemplate.opsForValue().get("stocks"));
            if (stocks > 0) {
//                stringRedisTemplate.opsForValue().decrement("stocks", 1);
                stocks = stocks - 1;
                System.out.println(serverPort+"成功减库存，剩余:" + stocks);
                stringRedisTemplate.opsForValue().set("stocks", stocks+"");
            } else {
                System.out.println(serverPort+"减库存失败，库存不足");
            }

        } catch (Exception e) {

        } finally {
            rLock.unlock();
            /*if (result){
                stringRedisTemplate.delete("lockKey");
            }*/
        }
        return R.ok().put("code", code);
    }

    @GetMapping("/sayHello")
    public String sayHello(){
        System.out.println("sayHello:"+serverPort);
        return "sayHello:"+serverPort;
    }

    @GetMapping("/sendDirectMessage")
    public R sendDirectMessage(){
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "test message, hello";
        String creatime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> map = new HashMap();
        map.put("messageId", messageId );
        map.put("messageData", messageData );
        map.put("creatime", creatime );

        //将消息绑定键值 TestDirectRouting 到交换机 TestDirectExchange
        rabbitTemplate.convertAndSend("TestDirectExchange", "TestDirectRouting",map);

        return R.ok().put("sendDirectMessage", map);
    }

    @GetMapping("/TestMessageAck")
    public String TestMessageAck() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "message: non-existent-exchange test message ";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", createTime);
        rabbitTemplate.convertAndSend("non-existent-exchange", "TestDirectRouting", map);
        return "ok";
    }

    @GetMapping("/TestMessageAck2")
    public String TestMessageAck2() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "message: lonelyDirectExchange test message ";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", createTime);
        rabbitTemplate.convertAndSend("lonelyDirectExchange", "TestDirectRouting", map);
        return "ok";
    }

    @GetMapping("/TestMessageAck3")
    public String TestMessageAck3() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "message: lonelyDirectExchange test message ";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", createTime);
        rabbitTemplate.convertAndSend("lonelyDirectExchange1", "TestDirectRouting1", map);
        return "ok";
    }

    @GetMapping("/TestMessageAck4")
    public String TestMessageAck4() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "message: lonelyDirectExchange test message ";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", createTime);
        rabbitTemplate.convertAndSend("TestDirectExchange", "TestDirectRouting", map);
        return "ok";
    }
}