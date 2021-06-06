package com.ldl.server;

import com.ldl.server.connector.Connector;
import com.ldl.server.container.Container;
import com.ldl.server.container.Engine;
import com.ldl.server.container.LifeCycleBase;
import com.ldl.server.container.StandardEngine;
import com.ldl.server.mapper.Mapper;

/**
 * @Author ldl
 * @Date 2021/6/6 18:09
 */
public class StandardService extends LifeCycleBase implements Service {

    private String name = null;
    protected Connector connector = null;
    private Engine engine = null;
    protected final Mapper mapper = new Mapper();

    public StandardService() {
        init();
    }

    @Override
    public Engine getContainer() {
        return engine;
    }

    @Override
    public void setContainer(Engine engine) {
        Engine oldEngine = this.engine;
        if (oldEngine != null) {
            oldEngine.setService(null);
        }

        this.engine = engine;
        if (this.engine != null) {
            this.engine.setService(this);
            this.engine.start();
        }

        if (oldEngine != null) {
            oldEngine.stop();
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Mapper getMapper() {
        return mapper;
    }

    @Override
    public Connector getConnector() {
        return connector;
    }

    @Override
    public void setConnector(Connector connector) {
        connector.setService(this);
        this.connector = connector;
    }

    @Override
    protected void initInternal() {
        System.out.println("service init");
        Engine engine = new StandardEngine();
        setContainer(engine);
        if (engine != null) {
            engine.init();
        }
        Connector connector = new Connector();
        setConnector(connector);
        if (connector != null) {
            connector.init();
        }
    }

    @Override
    protected void startInternal() {
        System.out.println("service start");
        if (engine != null) {
            engine.start();
        }
        if (connector != null) {
            System.out.println("connector start");
            connector.start();
        }
    }

    @Override
    protected void stopInternal() {
        if (connector != null) {
            connector.pause();
        }
        if (engine != null) {
            engine.stop();
        }
        if (connector != null) {
            connector.stop();
        }
    }

    @Override
    protected void destroyInternal() {
        if (connector != null) {
            connector.destroy();
        }
        if (engine != null) {
            engine.destroy();
        }
    }
}
