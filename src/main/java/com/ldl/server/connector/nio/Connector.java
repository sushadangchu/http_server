package com.ldl.server.connector.nio;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author ldl
 * @Date 2021/5/12 21:55
 */
public class Connector {
    private ThreadPoolExecutor poolExecutor;

    public Connector() {
        poolExecutor = new ThreadPoolExecutor(100, 100, 0L, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
    }

    public ThreadPoolExecutor getPoolExecutor() {
        return poolExecutor;
    }
}
