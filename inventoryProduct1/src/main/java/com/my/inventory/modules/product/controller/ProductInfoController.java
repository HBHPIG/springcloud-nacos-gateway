package com.my.inventory.modules.product.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.my.inventory.common.utils.R;
import com.my.inventory.common.utils.RedisService;
import com.my.inventory.common.utils.RedisUtil;
import com.my.inventory.modules.product.entity.ProductInfo;
import com.my.inventory.modules.product.service.ProductInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

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

    @Value("${server.port}")
    private int serverPort;

    @GetMapping("/query")
    @ApiOperation("查询商品")
    public R query(){
        List<ProductInfo> productInfoList = productInfoService.selectList(new EntityWrapper<ProductInfo>());
        redisUtil.set("productInfoList", productInfoList);
        stringRedisTemplate.opsForValue().set("test1122", "123");
        stringRedisTemplate.delete("test11");
        return R.ok().put( "productInfoList", redisUtil.get("productInfoList")).put("serverPort", serverPort);
    }

    @PostMapping("/reductInventory")
    public R reductInventory(){

        String code = "success";
        String lockKey = "product_101";
//        boolean result = stringRedisTemplate.opsForValue().setIfAbsent("lockKey", lockKey);
        /*String clientId = UUID.randomUUID().toString();

        boolean result = stringRedisTemplate.opsForValue().setIfAbsent(lockKey, clientId, 30, TimeUnit.SECONDS);
        if(!result){
            code= "error";
            return R.ok().put("code", code);
        }*/
        RLock rLock = redisson.getLock(lockKey);
        rLock.lock();
        try {
            Integer stocks = Integer.parseInt(stringRedisTemplate.opsForValue().get("stocks"));
            if (stocks > 0) {
                Integer realStocks = stocks - 1;
                System.out.println(serverPort+"成功减库存，剩余:" + realStocks);
                stringRedisTemplate.opsForValue().set("stocks", realStocks+"");
            } else {
                System.out.println(serverPort+"减库存失败，库存不足");
            }

        } catch (Exception e) {

        } finally {
//            if (clientId.equals(stringRedisTemplate.opsForValue().get(lockKey))){
//                stringRedisTemplate.delete(lockKey);
//            }
            rLock.unlock();
        }
        return R.ok().put("code", code);
    }

    @GetMapping("/sayHello")
    public String sayHello(){
        System.out.println("sayHello:"+serverPort);
        return "sayHello:"+serverPort;
    }
}
