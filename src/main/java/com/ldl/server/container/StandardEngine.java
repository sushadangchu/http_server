package com.ldl.server.container;

import com.ldl.server.Service;

import java.util.Locale;

/**
 * @Author ldl
 * @Date 2021/6/4 23:03
 */
public class StandardEngine extends ContainerBase implements Engine {

    private String defaultHost = null;
    private Service service = null;

    public StandardEngine() {

    }

    @Override
    public String getDefaultHost() {
        return defaultHost;
    }

    @Override
    public void setDefaultHost(String host) {
        if (host == null) {
            this.defaultHost = null;
        } else {
            this.defaultHost = host.toLowerCase(Locale.ENGLISH);
        }
    }

    @Override
    public Service getService() {
        return this.service;
    }

    @Override
    public void setService(Service service) {
        this.service = service;
    }

    @Override
    public void addChild(Container child) {
        if (!(child instanceof Host)) {
            throw new IllegalArgumentException("Engine addChild error");
        }
        super.addChild(child);
    }

    @Override
    public void setParent(Container parent) {
        throw new IllegalArgumentException("Engine setParent error");
    }
}
