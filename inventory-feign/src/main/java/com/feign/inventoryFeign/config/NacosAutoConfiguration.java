package com.feign.inventoryFeign.config;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Configuration
public class NacosAutoConfiguration {

    @Autowired
    private Environment environment;

//    @Value("${spring.cloud.nacos.config.server-addr}")
//    private String serverAddr;

    @Bean
    public ConfigService configService() throws NacosException{

//        String url = environment.getProperty("127.0.0.1:8848");
        String serverAddr = "127.0.0.1:8848";

        String dataId = "stock-nacos-provider-dev.yaml";

        String namespace = "f075a12d-326c-4ef2-9f6a-8fc64f4a43a6";

        String group = "DEFAULT_GROUP";

        Properties properties = new Properties();
        properties.put("serverAddr",serverAddr);
//        properties.put("namespace",namespace);
//        properties.put("serverAddr",serverAddr);

        ConfigService configService = NacosFactory.createConfigService(properties);
        return configService;
    }

    /*@Bean
    public NacosOperation nacosOperation() {
        return new NacosOperation();
    }*/

}
