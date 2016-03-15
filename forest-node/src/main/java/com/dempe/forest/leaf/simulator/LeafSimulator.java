package com.dempe.forest.leaf.simulator;

import com.dempe.forest.leaf.controller.ISampleController;
import com.dempe.forest.rpc.client.Future;
import com.dempe.forest.rpc.client.RPCClient;
import com.dempe.forest.rpc.client.proxy.ObjectProxy;
import com.dempe.forest.rpc.transport.protocol.PacketData;

/**
 * Created with IntelliJ IDEA.
 * User: Dempe
 * Date: 2016/2/3
 * Time: 12:55
 * To change this template use File | Settings | File Templates.
 */
public class LeafSimulator {
    public static void main(String[] args) throws Exception {
        test();
    }


    public static void test2() {
        ISampleController client = RPCClient.proxyBuilder(ISampleController.class)
                .withServerNode("127.0.0.1", 8888)
                .build();
        String hello = client.hello("hello world!");
        System.out.println(">>>>>>>>>>>>>>>>" + hello);
    }

    public static void test() throws Exception {
        ObjectProxy objectProxy = RPCClient.proxyBuilder(ISampleController.class)
                .withServerNode("127.0.0.1", 8888)
                .buildAsyncObjPrx();
        Future<PacketData> hello = objectProxy.call("hello", "6666");
        PacketData await = hello.await();
        byte[] data = await.getData();
        System.out.println(new String(data));
    }


}
