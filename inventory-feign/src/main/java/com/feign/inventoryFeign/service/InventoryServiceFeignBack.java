package com.feign.inventoryFeign.service;

import com.feign.inventoryFeign.config.R;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * Feign服务类
 * </p>
 *
 * @author BH.huang
 * @since 2021-07-07
 */
@Component
public class InventoryServiceFeignBack implements InventoryServiceFeign {


    @Override
    public R query () {
        return R.error().put("error", "断路由");
    }

    @Override
    public String sayHello () {
        System.out.println("进入断路由");
        return "进入断路由";
    }

    @Override
//    @Transactional
    public R reductInventory () {
        return null;
    }

    @Override
    public R sendDirectMessage () {
        return R.ok().put("error", "消息发送到rabbitMQ失败！！！");
    }
}
