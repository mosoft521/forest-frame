package com.dempe.forest.core.config;

/**
 * Created with IntelliJ IDEA.
 * User: Dempe
 * Date: 2016/4/29
 * Time: 18:14
 * To change this template use File | Settings | File Templates.
 */
public class ProtocolConfig {

    private String protocolName;

    public String getProtocolName() {
        return protocolName;
    }

    public void setProtocolName(String protocolName) {
        this.protocolName = protocolName;
    }

    public static enum ProtoEnum {
        FOREST("forest");
        private String protocolName;

        private ProtoEnum(String protocolName) {
            this.protocolName = protocolName;
        }

        public String value() {
            return protocolName;
        }
    }
}
