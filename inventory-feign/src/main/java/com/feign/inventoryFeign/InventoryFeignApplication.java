package com.feign.inventoryFeign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author HBH
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients("com.feign.inventoryFeign.service")
// 在配置文件配置服务降级
//@EnableHystrix
@RefreshScope
public class InventoryFeignApplication extends SpringBootServletInitializer {

    public static void main (String[] args) {
        SpringApplication.run(InventoryFeignApplication.class, args);
    }

//    @Bean
//    @LoadBalanced
//    RestTemplate RestTemplate(){
//        return new RestTemplate();
//    }


}
