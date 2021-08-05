package com.my.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * @author HBH
 */
@SpringBootApplication
@RestController
public class ConfigClientApplication {

    public static void main (String[] args) {
        SpringApplication.run(ConfigClientApplication.class, args);
    }

    @Value("${name}")
    private String name;

    @GetMapping("/getName")
    public String getName(){

        return name;
    }
}
