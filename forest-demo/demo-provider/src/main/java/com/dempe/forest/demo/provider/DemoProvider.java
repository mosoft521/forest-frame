package com.dempe.forest.demo.provider;

import com.dempe.forest.core.config.ApplicationConfig;
import com.dempe.forest.core.config.ProtocolConfig;
import com.dempe.forest.core.config.RegistryConfig;
import com.dempe.forest.core.config.ServiceConfig;

/**
 * Created with IntelliJ IDEA.
 * User: Dempe
 * Date: 2016/4/29
 * Time: 18:10
 * To change this template use File | Settings | File Templates.
 */
public class DemoProvider {
    public static void main(String[] args) {
        // 全局配置
        ApplicationConfig application = new ApplicationConfig();
        application.setName("demo");

        // 注册中心配置
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress("");
        registryConfig.setName("");


        // 协议配置
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setProtocolName(ProtocolConfig.ProtoEnum.FOREST.value());

        //service服务发布
        ServiceConfig service = new ServiceConfig();
        service.setProtocol(protocolConfig);
        service.setRegistry(registryConfig);
        service.setApplication(application);

        service.export();

    }

}
