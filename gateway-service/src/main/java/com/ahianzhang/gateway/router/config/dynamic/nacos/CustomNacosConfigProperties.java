package com.ahianzhang.gateway.router.config.dynamic.nacos;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author ahianzhang
 * @version 1.0
 * @date 2020/8/11 10:03 AM
 **/
@Configuration
public class CustomNacosConfigProperties {

    public static String  NACOS_CONFIG_SERVER_ADDRESS;
    public static String NACOS_CONFIG_DATA_ID;
    public static String NACOS_CONFIG_GROUP_ID;

    @Value("${xuanji.gateway.dynamic-route.nacos.dataId}")
    private void setNacosConfigDataId(String nacosConfigDataId) {
        NACOS_CONFIG_DATA_ID = nacosConfigDataId;
    }

    @Value("${spring.cloud.nacos.discovery.server-addr}")
    private void setNacosConfigServerAddress(String nacosConfigServerAddress) {
        NACOS_CONFIG_SERVER_ADDRESS = nacosConfigServerAddress;
    }


    @Value("${xuanji.gateway.dynamic-route.nacos.group:DEFAULT_GROUP}")
    private void setNacosConfigGroupId(String nacosConfigGroupId) {
        NACOS_CONFIG_GROUP_ID = nacosConfigGroupId;
    }
}
