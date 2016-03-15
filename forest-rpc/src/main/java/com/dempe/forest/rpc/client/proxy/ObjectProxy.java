package com.dempe.forest.rpc.client.proxy;


import com.dempe.forest.rpc.client.Callback;
import com.dempe.forest.rpc.client.Future;
import com.dempe.forest.rpc.transport.protocol.PacketData;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ObjectProxy<T> extends BaseObjectProxy<T> implements InvocationHandler {

    public ObjectProxy(String host, int port) {
        super(host, port);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        if (Object.class == method.getDeclaringClass()) {
            String name = method.getName();
            if ("equals".equals(name)) {
                return proxy == args[0];
            } else if ("hashCode".equals(name)) {
                return System.identityHashCode(proxy);
            } else if ("toString".equals(name)) {
                return proxy.getClass().getName() + "@" +
                        Integer.toHexString(System.identityHashCode(proxy)) +
                        ", with InvocationHandler " + this;
            } else {
                throw new IllegalStateException(String.valueOf(method));
            }
        }
        PacketData packet = createPacket(method, args);
        Future<PacketData> send = send(packet);
        PacketData packetData = send.await();
        byte[] data = packetData.getData();
        return new String(data);
    }

    public Future<PacketData> call(String methodName, Object... args) throws Exception {
        Method method = getMethod(methodName, args);
        PacketData packet = createPacket(method, args);
        return send(packet);
    }

    public void notify(String methodName, Callback<T> callback, Object... args) throws Exception {
        Method method = getMethod(methodName, args);
        PacketData packet = createPacket(method, args);
        send(packet, callback);
    }

    private Method getMethod(String methodName, Object[] args) throws NoSuchMethodException {
        Class<?>[] parameterTypes = new Class<?>[args.length];
        for (int i = 0; i < args.length; i++) {
            parameterTypes[i] = args[i].getClass();
        }
        Method method = clazz.getMethod(methodName, parameterTypes);
        if (method == null) {
            new IllegalArgumentException("method is null for methodName=" + methodName);
        }
        return method;
    }


}
