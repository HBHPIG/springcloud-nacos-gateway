package com.my.config;

import org.springframework.cloud.bootstrap.config.PropertySourceLocator;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;

import java.util.HashMap;
import java.util.Map;

@Order(value = Ordered.HIGHEST_PRECEDENCE+1)
public class CustomPropertySourceLocator implements PropertySourceLocator {

    private static final String OVERRIDE_ADD_MAPPING = "spring.resources.add-mappings";

    @Override
    public PropertySource<?> locate (Environment environment) {
        Map<String, Object> map = new HashMap<>(2);
        map.put(OVERRIDE_ADD_MAPPING, "true");
        return new MapPropertySource("custom", map);
    }
}
