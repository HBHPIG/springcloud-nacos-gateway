package com.feign.inventoryFeign.controller;


import com.feign.inventoryFeign.config.R;
import com.feign.inventoryFeign.service.InventoryServiceFeign;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * <p>
 *
 * </p>
 *
 * @author BH.huang
 * @since 2021-07-07
 */
@RestController
@RequestMapping("/feign")
@Api(tags = "test")
public class InventoryController {

    @Autowired
    private InventoryServiceFeign inventoryServiceFeign;

//    @Autowired
//    RestTemplate restTemplate;

    @GetMapping("/productInfo/query")
    public R query(){

        return inventoryServiceFeign.query();
    }

    @PostMapping("/productInfo/reductInventory")
    public R reductInventory(){

        Runnable r = () -> {
            inventoryServiceFeign.reductInventory();
        };
        for (int i = 0 ; i<10000 ;i++){
            new Thread(r,"reductInventory"+i).start();
        }

        return R.ok().put("run","rrrr");
    }

    @GetMapping("/productInfo/sayHello")
    public String sayHello(){

        return inventoryServiceFeign.sayHello();
//        restTemplate.getForObject("", String.class);
//        return restTemplate.getForObject("http://PROVIDER-INVENTORY/productInfo/sayHello",String.class);
    }

    @GetMapping("/productInfo/sendDirectMessage")
    public R sendDirectMessage(){

        return inventoryServiceFeign.sendDirectMessage();
    }

}
