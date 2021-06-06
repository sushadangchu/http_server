package com.ldl.server;

import com.ldl.server.connector.Connector;
import com.ldl.server.container.Engine;
import com.ldl.server.container.LifeCycle;
import com.ldl.server.mapper.Mapper;

/**
 * @Author ldl
 * @Date 2021/6/4 22:49
 */
public interface Service extends LifeCycle {
    public Engine getContainer();
    public void setContainer(Engine engine);
    public String getName();
    public void setName(String name);
    Mapper getMapper();
    public Connector getConnector();
    public void setConnector(Connector connector);
}
