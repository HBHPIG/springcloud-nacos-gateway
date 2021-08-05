package com.feign.inventoryFeign.service;

import com.feign.inventoryFeign.config.FeignConfig;
import com.feign.inventoryFeign.config.R;
//import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * <p>
 * Feign服务类
 * </p>
 *
 * @author BH.huang
 * @since 2021-07-07
 */
@Component
@FeignClient(name = "provider-inventory",fallback = InventoryServiceFeignBack.class)
public interface InventoryServiceFeign {

    /**
     * 查询库存
     * @return
     */
    @GetMapping("/productInfo/query")
    R query ();

    /**
     * cs
     * @return
     */
    @GetMapping("/productInfo/sayHello")
    String sayHello ();

    /**
     * 减库存
     * @return
     */
    @PostMapping("/productInfo/reductInventory")
    R reductInventory();

    /**
     * 发送消息到drabbitMq
     * @return
     */
    @GetMapping("/productInfo/sendDirectMessage")
    R sendDirectMessage();
}
