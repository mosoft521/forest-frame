/*
 * Copyright 2002-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dempe.forest.rpc.client;

import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author xuyuepeng, sunzhongyi, lijianbin
 */
public class ChannelPool {
    private static final Logger LOGGER = Logger.getLogger(ChannelPool.class.getName());

    private final PooledObjectFactory<Connection> objectFactory;
    private final GenericObjectPool<Connection> pool;

    public ChannelPool(CommonClient rpcClient) {
        objectFactory = new ChannelPoolObjectFactory(rpcClient);
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        pool = new GenericObjectPool<Connection>(objectFactory, config);

    }

    public Connection getChannel() throws Exception {
        Connection channel = pool.borrowObject();
        return channel;
    }

    public void returnChannel(Connection channel) {
        pool.returnObject(channel);

    }

    public void stop() {
        try {
            if (pool != null) {
                pool.clear();
                pool.close();
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "stop channel failed!", e);
        }
    }
}
