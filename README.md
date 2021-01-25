# 璇玑服务管理平台
璇玑服务管理平台是为了提高多服务环境下系统的可靠性，本平台使用 Spring Cloud Alibaba 做为主要的技术组件，用简单的方式支持更多的功能。
## 服务发现和服务配置
[spring cloud alibaba nacos](https://nacos.io/zh-cn/docs/quick-start-spring-cloud.html) 
## 服务通信
- Rest 模式：open feign
- RPC 模式：待实现
## 服务监控
待实现
## 服务熔断和降级
待实现
## 网关服务
作为 API 的一个独立的防火墙，统一提供服务流量控制、鉴权、路由等功能。并未采用微服务中 Gateway/BFF 模式的设计（成熟度和成本原因）
[详细介绍](gateway-service/README.md)
## 鉴权服务
遵循 OAuth2 授权协议自研服务
[详细介绍](auth-service/README.md)
