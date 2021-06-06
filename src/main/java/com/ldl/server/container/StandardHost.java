package com.ldl.server.container;

import java.util.Locale;

/**
 * @Author ldl
 * @Date 2021/6/4 23:19
 */
public class StandardHost extends ContainerBase implements Host {

    private String appBase = "webapps";

    public StandardHost() {

    }

    @Override
    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("StandardHost setName error");
        }
        name = name.toLowerCase(Locale.ENGLISH);
    }

    @Override
    public void addChild(Container child) {
        if (!(child instanceof Context)) {
            throw new IllegalArgumentException("StandardHost addChild error");
        }
        super.addChild(child);
    }
}
