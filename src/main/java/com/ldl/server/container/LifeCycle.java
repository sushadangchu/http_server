package com.ldl.server.container;

/**
 * @Author ldl
 * @Date 2021/6/4 21:43
 */
public interface LifeCycle {
    public void init();
    public void start();
    public void stop();
    public void destroy();
}
