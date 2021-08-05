package com.my.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.bootstrap.BootstrapConfiguration;
import org.springframework.cloud.config.server.EnableConfigServer;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
/**
 * @author HBH
 */
@SpringBootApplication
//@EnableEurekaClient
@EnableConfigServer
@BootstrapConfiguration
public class ConfigServerApplication {

    public static void main (String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
    }

}
