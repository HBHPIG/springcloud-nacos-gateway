package com.gw.gateway.config;

import lombok.AllArgsConstructor;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.support.NameUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * @author HBH
 */
//@Component
//@Primary
//@AllArgsConstructor
public class SwaggerProvider implements SwaggerResourcesProvider {


    public static final String API_URI = "/v2/api-docs";
    private final RouteLocator routeLocator = new routeLocatorConfig();
    private final GatewayProperties gatewayProperties = new GatewayProperties();

    @Override
    public List<SwaggerResource> get () {
        List<SwaggerResource> list = new ArrayList<>();
        List<String> routes = new ArrayList<>();
        //取出gateway的route
        routeLocator.getRoutes().subscribe(route -> routes.add(route.getId()));
        //结合配置的route-路径(Path)，和route过滤，只获取有效的route节点
        gatewayProperties.getRoutes().stream().filter(routeDefinition -> routes.contains(routeDefinition.getId()))
                .forEach(routeDefinition -> routeDefinition.getPredicates().stream()
                        .filter(predicateDefinition -> "Path".equalsIgnoreCase(predicateDefinition.getName()))
                        //过滤掉hotel-auth和hotel-codegen模块，不加载
                        .filter(predicateDefinition -> !"hotel-auth".equalsIgnoreCase(routeDefinition.getId()))
                        .filter(predicateDefinition -> !"hotel-codegen".equalsIgnoreCase(routeDefinition.getId()))
                        .forEach(predicateDefinition -> list.add(swaggerResource(routeDefinition.getId(),
                                predicateDefinition.getArgs().get(NameUtils.GENERATED_NAME_PREFIX + "0")
                                        .replace("/**", API_URI)))));
        return list;
    }

    private SwaggerResource swaggerResource(String name, String location) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion("2.0");
        return swaggerResource;
    }

    class routeLocatorConfig implements RouteLocator {

        @Override
        public Flux<Route> getRoutes () {

            return null;
        }
    }
}


