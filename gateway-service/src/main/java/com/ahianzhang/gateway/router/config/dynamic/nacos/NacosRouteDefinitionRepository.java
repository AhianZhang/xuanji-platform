package com.ahianzhang.gateway.router.config.dynamic.nacos;

import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.cloud.nacos.NacosConfigProperties;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.context.ApplicationEventPublisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.Executor;

/**
 * @author ahianzhang
 * @version 1.0
 * @date 2020/8/10 4:37 PM
 **/
public final class NacosRouteDefinitionRepository implements RouteDefinitionRepository {
    private static final Logger log = LoggerFactory.getLogger(NacosRouteDefinitionRepository.class);


    private final ApplicationEventPublisher publisher;
    private NacosConfigProperties nacosConfigProperties;

    private final String nacosConfigDataId = CustomNacosConfigProperties.NACOS_CONFIG_DATA_ID;
    private final String nacosConfigGroupId = CustomNacosConfigProperties.NACOS_CONFIG_GROUP_ID;

    ConfigService configService = new NacosConfigManager(nacosConfigProperties).getConfigService();

    public NacosRouteDefinitionRepository(ApplicationEventPublisher publisher, NacosConfigProperties nacosConfigProperties) {

        this.publisher = publisher;
        this.nacosConfigProperties = nacosConfigProperties;
        log.debug("initialization NacosRouteDefinitionRepository class");
        registry();

    }

    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        String config = null;
        try {
            config = configService.getConfig(nacosConfigDataId, nacosConfigGroupId, 15000);
        } catch (NacosException e) {
            log.warn(e.toString());
        }
        if (config != null) {
        return getRouting(config);
        }
        log.info(config);
        return Flux.empty();
    }

    @Override
    public Mono<Void> save(Mono<RouteDefinition> route) {
        return null;
    }

    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        return null;
    }


    private void registry() {
        try {
            configService.addListener(nacosConfigDataId, nacosConfigGroupId, nacosConfigListener());
        } catch (NacosException e) {
            log.warn(e.toString());
        }


    }


    private Listener nacosConfigListener() {
        return new Listener() {
            @Override
            public Executor getExecutor() {
                return null;
            }

            @Override
            public void receiveConfigInfo(String configInfo) {
                publisher.publishEvent(new RefreshRoutesEvent(this));
            }
        };
    }


    @SuppressWarnings("unchecked")
    private Flux<RouteDefinition> getRouting(String jsonConfig){

        if (jsonConfig.isEmpty() || jsonConfig.length() <= 2){
            log.warn("remote config is empty!");
            return Flux.empty();
        }
        JSONObject jo = JSONObject.parseObject(jsonConfig);
        JSONArray routes = jo.getJSONArray("routes");
        List<RouteDefinition> list = routes.toJavaList(RouteDefinition.class);
        return Flux.fromIterable(list);
    }
}
