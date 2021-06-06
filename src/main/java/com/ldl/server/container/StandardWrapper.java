package com.ldl.server.container;

import com.ldl.server.Servlet;
import com.ldl.server.WebApp;

import java.lang.reflect.Method;
import java.util.HashSet;

/**
 * @Author ldl
 * @Date 2021/6/6 16:27
 */
public class StandardWrapper extends ContainerBase implements Wrapper {

    protected volatile Servlet instance = null;
    protected String servletClass = null;

    public StandardWrapper() {

    }

    @Override
    public void load() {
        instance = loadServlet();
    }

    public Servlet loadServlet() {
        if (instance != null) {
            return instance;
        }
        if (servletClass == null) {
            throw new IllegalArgumentException("servletClass null");
        }
        Servlet servlet = WebApp.getServletFromUri(servletClass);

        return servlet;
    }

    @Override
    public void unload() {
        instance.destroy();
    }

    @Override
    public String getServletClass() {
        return servletClass;
    }

    @Override
    public void setServletClass(String servletClass) {
        this.servletClass = servletClass;
    }

    @Override
    public String[] getServletMethods() {
        instance = loadServlet();

        Class<? extends Servlet> servletClazz = instance.getClass();
        HashSet<String> allow = new HashSet<>();

        Method[] methods = servletClazz.getDeclaredMethods();
        for (int i=0; methods != null && i<methods.length; i++) {
            Method m = methods[i];

            if (m.getName().equals("doGet")) {
                allow.add("GET");
                allow.add("HEAD");
            } else if (m.getName().equals("doPost")) {
                allow.add("POST");
            } else if (m.getName().equals("doPut")) {
                allow.add("PUT");
            } else if (m.getName().equals("doDelete")) {
                allow.add("DELETE");
            }
        }

        String[] methodNames = new String[allow.size()];
        return allow.toArray(methodNames);
    }

    @Override
    public Servlet getServlet() {
        return instance;
    }

    @Override
    public void setServlet(Servlet servlet) {
        instance = servlet;
    }

    @Override
    public void addChild(Container child) {
        throw new IllegalStateException("wrapper no child");
    }

    @Override
    public void setParent(Container container) {
        if ((container != null) && !(container instanceof Context)) {
            throw new IllegalArgumentException("wrapper setParent error");
        }
        super.setParent(container);
    }
}
