package com.dempe.forest.core.config;

import com.dempe.forest.core.Service;

/**
 * Created with IntelliJ IDEA.
 * User: Dempe
 * Date: 2016/4/29
 * Time: 18:07
 * To change this template use File | Settings | File Templates.
 */
public class ServiceConfig implements Service{
    private ApplicationConfig application;
    private RegistryConfig registry;
    private ProtocolConfig protocol;

    public ApplicationConfig getApplication() {
        return application;
    }

    public void setApplication(ApplicationConfig application) {
        this.application = application;
    }

    public RegistryConfig getRegistry() {
        return registry;
    }

    public void setRegistry(RegistryConfig registry) {
        this.registry = registry;
    }

    public ProtocolConfig getProtocol() {
        return protocol;
    }

    public void setProtocol(ProtocolConfig protocol) {
        this.protocol = protocol;
    }

    @Override
    public void export() {

    }
}
