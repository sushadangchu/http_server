package com.ldl.server.container;

import java.util.HashMap;

/**
 * @Author ldl
 * @Date 2021/6/4 22:09
 */
public abstract class ContainerBase extends LifeCycleBase implements Container {
    protected String name = null;
    protected Container parent = null;
    protected final HashMap<String, Container> children = new HashMap<>();

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("ContainerBase setName error");
        }
        this.name = name;
    }

    @Override
    public Container getParent() {
        return parent;
    }

    @Override
    public void setParent(Container parent) {
        this.parent = parent;
    }

    @Override
    public void addChild(Container child) {
        if (children.get(child.getName()) != null) {
            throw new IllegalArgumentException("addChild:  Child name '" + child.getName() + "' is not unique");
        }
        child.setParent(this);
        children.put(child.getName(), child);

        child.start();
    }

    @Override
    public Container findChild(String name) {
        if (name == null) {
            return null;
        }
        return children.get(name);
    }

    @Override
    public Container[] findChildren() {
        Container results[] = new Container[children.size()];
        return children.values().toArray(results);
    }

    @Override
    public void removeChild(Container child) {
        if (child == null) {
            return;
        }

        child.stop();
        child.destroy();

        if (children.get(child.getName()) == null) {
            return;
        }
        children.remove(child);
    }

    @Override
    protected void initInternal() {

    }

    @Override
    protected void startInternal() {
        Container[] results = findChildren();
        for (int i = 0; i < results.length; i++) {
            results[i].start();
        }
    }

    @Override
    protected void stopInternal() {
        Container[] results = findChildren();
        for (int i = 0; i < results.length; i++) {
            results[i].stop();
        }
    }

    @Override
    protected void destroyInternal() {
        for (Container child : findChildren()) {
            removeChild(child);
        }
        if (parent != null) {
            parent.removeChild(this);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Container parent = getParent();
        if (parent != null) {
            sb.append(parent.toString());
            sb.append('.');
        }
        sb.append(this.getClass().getSimpleName());
        sb.append('[');
        sb.append(getName());
        sb.append(']');
        return sb.toString();
    }
}
