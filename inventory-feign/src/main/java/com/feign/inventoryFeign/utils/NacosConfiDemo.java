package com.feign.inventoryFeign.utils;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;

import java.util.Properties;

public class NacosConfiDemo {

    public static void main (String[] args) throws NacosException {
        String serverAddr = "127.0.0.1:8848";

        String dataId = "stock-nacos-provider-dev.yaml";

        String namespace = "f075a12d-326c-4ef2-9f6a-8fc64f4a43a6";

        String group = "DEFAULT_GROUP";

        Properties properties = new Properties();
        properties.put("serverAddr",serverAddr);
//        properties.put("namespace",namespace);
//        properties.put("serverAddr",serverAddr);

        ConfigService configService = NacosFactory.createConfigService(properties);

        String config = configService.getConfig(dataId, group, 5000);
        System.out.println(config);
    }
}
