# springcloud-nacos-gateway

#### 介绍
整合springcloud+gateway+nacos+redis+rabbitMQ。
用nacos替换eureka，服务调用和熔断用openfeign替换feign、ribbon、hystrix。
缓存、分布式锁用redis
消息中间件用rabbitMQ

#### 软件架构
软件架构说明


#### 安装教程

1.  redis安装
2.  nacos安装
3.  RabbitMQ安装之前，需要安装erlang工具

#### 使用说明
1.  gateway 网关，配置路由、登陆拦截
2.  eureka-server eureka注册中心，服务发现和注册
3.  config-server 配置中心
4.  config-client 读取配置中心
5.  nacos-config  读取nacos配置中心
6.  inventoryProduct 向eureka和nacos注册中心注册服务，rabbitMQ发送消息等
7.  inventory-feign 调用eureka、nacos服务，做负载均衡、服务降级等


#### 参与贡献

1.  Fork 本仓库
2.  新建 Feat_xxx 分支
3.  提交代码
4.  新建 Pull Request


#### 特技

1.  各种版本冲突，注意springboot、gateway、nacos等版本问题，稍加不注意，就会出现各种各样的错误。
2.  增加介绍RabbitMQ消息的推送、消费等功能，关于消息自动或者手动确认模式
