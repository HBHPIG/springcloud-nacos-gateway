package com.my.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author HBH
 */
@Configuration
public class CustomBootstrapConfiguration {

    @Bean("customPropertySourceLocator")
    public CustomPropertySourceLocator propertySourceLocator(){

        return new CustomPropertySourceLocator();
    }
}
