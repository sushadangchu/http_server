package com.ldl.server.container;

/**
 * @Author ldl
 * @Date 2021/6/4 21:46
 */
public abstract class LifeCycleBase implements LifeCycle {

    @Override
    public void init() {
        initInternal();
    }

    protected abstract void initInternal();

    @Override
    public void start() {
        startInternal();
    }

    protected abstract void startInternal();

    @Override
    public void stop() {
        stopInternal();
    }

    protected abstract void stopInternal();

    @Override
    public void destroy() {
        destroyInternal();
    }

    protected abstract void destroyInternal();
}
