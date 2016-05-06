package com.dempe.forest.core.config;

/**
 * Created with IntelliJ IDEA.
 * User: Dempe
 * Date: 2016/4/29
 * Time: 18:04
 * To change this template use File | Settings | File Templates.
 */
public class RegistryConfig {
    private String name;

    private String regProto;

    // 注册中心地址，支持多个ip+port，格式：ip1:port1,ip2:port2,ip3，如果没有port，则使用默认的port
    private String address;

    private int port;

    // 注册中心请求超时时间(毫秒)
    private Integer requestTimeout;

    // 注册中心会话超时时间(毫秒)
    private Integer registrySessionTimeout;

    // 失败后重试的时间间隔
    private Integer registryRetryPeriod;

    // 启动时检查注册中心是否存在
    private String check;

    // 在该注册中心上服务是否暴露
    private Boolean register;

    // 在该注册中心上服务是否引用
    private Boolean subscribe;

    private Boolean isDefault;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegProto() {
        return regProto;
    }

    public void setRegProto(String regProto) {
        this.regProto = regProto;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public Integer getRequestTimeout() {
        return requestTimeout;
    }

    public void setRequestTimeout(Integer requestTimeout) {
        this.requestTimeout = requestTimeout;
    }

    public Integer getRegistrySessionTimeout() {
        return registrySessionTimeout;
    }

    public void setRegistrySessionTimeout(Integer registrySessionTimeout) {
        this.registrySessionTimeout = registrySessionTimeout;
    }

    public Integer getRegistryRetryPeriod() {
        return registryRetryPeriod;
    }

    public void setRegistryRetryPeriod(Integer registryRetryPeriod) {
        this.registryRetryPeriod = registryRetryPeriod;
    }

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }

    public Boolean getRegister() {
        return register;
    }

    public void setRegister(Boolean register) {
        this.register = register;
    }

    public Boolean getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(Boolean subscribe) {
        this.subscribe = subscribe;
    }

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }
}
