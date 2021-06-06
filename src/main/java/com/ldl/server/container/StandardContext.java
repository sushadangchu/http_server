package com.ldl.server.container;

/**
 * @Author ldl
 * @Date 2021/6/6 16:07
 */
public class StandardContext extends ContainerBase implements Context {

    public StandardContext() {

    }

    @Override
    public void addChild(Container child) {
        if (!(child instanceof Host)) {
            throw new IllegalArgumentException("Context addChild error");
        }
        super.addChild(child);
    }

    @Override
    protected void startInternal() {
        for (Container child : findChildren()) {
            child.start();
        }
    }
}
