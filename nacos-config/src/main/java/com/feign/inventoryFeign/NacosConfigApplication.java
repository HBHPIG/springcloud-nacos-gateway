package com.feign.inventoryFeign;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//import org.springframework.cloud.netflix.hystrix.EnableHystrix;
//import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 *
 * @author HBH
 */
@SpringBootApplication
@EnableDiscoveryClient
//@EnableFeignClients("com.feign.inventoryFeign.service")
//@Configuration
//@EnableHystrix
@RestController
@RefreshScope
public class NacosConfigApplication extends SpringBootServletInitializer {

    public static void main (String[] args) {
        SpringApplication.run(NacosConfigApplication.class, args);
    }

//    @Bean
//    @LoadBalanced
//    RestTemplate RestTemplate(){
//        return new RestTemplate();
//    }

    @Value("${server.servlet.context-path}")
    private String name;

    @GetMapping("/getPort")
    public String getPort(){
        return name;
    }

}
