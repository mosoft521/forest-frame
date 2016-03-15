package com.dempe.forest.rpc.client.proxy;

import com.dempe.forest.rpc.client.DefaultClient;
import com.dempe.forest.rpc.core.RPCExporter;
import com.dempe.forest.rpc.core.RPCService;
import com.dempe.forest.rpc.transport.protocol.PacketData;
import com.dempe.forest.rpc.transport.protocol.ProtocolConstant;
import com.dempe.forest.rpc.utils.Pack;
import com.dempe.forest.rpc.utils.PathUtil;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Method;
import java.util.Map;

public class BaseObjectProxy<T> extends DefaultClient {

    protected Class<T> clazz;

    protected String serviceName;

    private Map<String, PacketData> packetCache = Maps.newHashMap();

    public BaseObjectProxy(String host, int port) {
        super(host, port);
    }

    public void setClazz(Class<T> clazz) {
        this.clazz = clazz;
        RPCExporter rpcExporter = clazz.getAnnotation(RPCExporter.class);
        if (rpcExporter == null) {
            new IllegalArgumentException("clazz :" + clazz.getSimpleName() + " ,RPCExporter is null");
        }
        this.serviceName = rpcExporter.serviceName();
        if (StringUtils.isBlank(serviceName)) {
            serviceName = clazz.getSimpleName();
        }
    }

    PacketData createPacket(Method method, Object[] args) {
        String methodName = method.getName();
        if (serviceName == null) {
            new IllegalArgumentException("method:" + methodName + "serviceName is null,please setClazz first");
        }
        String serviceKey = PathUtil.buildPath(serviceName, method.getName());
        PacketData packetData = packetCache.get(serviceKey);
        if (packetData == null) {
            RPCService rpcService = method.getAnnotation(RPCService.class);
            if (rpcService == null) {
                new IllegalArgumentException("method:" + methodName + "RPCService is null");
            }
            String funcName = rpcService.methodName();
            if (StringUtils.isBlank(funcName)) {
                funcName = method.getName();
            }
            packetData = new PacketData();
            packetData.magicCode(ProtocolConstant.MAGIC_CODE);
            packetData.compressType(rpcService.compressType().value());
            packetData.serviceName(serviceName);
            packetData.methodName(funcName);
            packetCache.put(serviceKey, packetData.copy());
        }

        if (args != null && args.length > 0) {
            byte[] bytes = encodeArgs(args);
            if (bytes != null) {
                packetData.data(bytes);
            }
        }
        return packetData;
    }


    private byte[] encodeArgs(Object[] args) {
        Pack pack = new Pack();
        for (Object arg : args) {
            if (arg instanceof String) {
                String value = (String) arg;
                pack.putVarstr(value);
            } else if (arg instanceof Integer) {
                Integer value = (Integer) arg;
                pack.putInt(value);
            } else if (arg instanceof Short) {
                Short value = (Short) arg;
                pack.putShort(value);
            } else if (arg instanceof Long) {
                Long value = (Long) arg;
                pack.putLong(value);
            } else if (arg instanceof Double) {
                Double value = (Double) arg;
                pack.putDouble(value);
            } else if (arg instanceof Float) {
                Float value = (Float) arg;
                pack.putFloat(value);
            }
        }
        // encode args
        byte data[] = pack.getOriBuffer().array();
        return data;

    }

}
