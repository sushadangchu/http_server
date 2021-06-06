package com.ldl.server.container;

import com.ldl.server.connector.nio.Connector;

import java.util.HashMap;

/**
 * @Author ldl
 * @Date 2021/6/4 22:00
 */
public interface Container extends LifeCycle {
    public String getName();
    public void setName(String name);
    public Container getParent();
    public void setParent(Container parent);
    public void addChild(Container child);
    public Container findChild(String name);
    public Container[] findChildren();
    public void removeChild(Container child);
}
