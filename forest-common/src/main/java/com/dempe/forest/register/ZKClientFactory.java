package com.dempe.forest.register;

import org.aeonbits.owner.ConfigFactory;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

/**
 * Created with IntelliJ IDEA.
 * User: Dempe
 * Date: 2016/1/29
 * Time: 16:58
 * To change this template use File | Settings | File Templates.
 */
public class ZKClientFactory {

    public static CuratorFramework getZKClient() {
        ZKConfig cfg = ConfigFactory.create(ZKConfig.class);
        return CuratorFrameworkFactory.newClient(cfg.connStr(), new ExponentialBackoffRetry(1000, 3));
    }

    public static void main(String[] args) throws Exception {
        CuratorFramework zkClient = getZKClient();
        zkClient.getCuratorListenable().addListener(new CuratorListener() {
            @Override
            public void eventReceived(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
                WatchedEvent watchedEvent = curatorEvent.getWatchedEvent();
                String path = watchedEvent.getPath();
                Watcher.Event.EventType type = watchedEvent.getType();
            }
        });
        byte[] bytes = zkClient.getData().watched().forPath("");

    }
}
