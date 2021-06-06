package com.ldl.server.container;

import com.ldl.server.Servlet;

/**
 * @Author ldl
 * @Date 2021/6/6 16:21
 */
public interface Wrapper {
    public void load();
    public void unload();
    public String getServletClass();
    public void setServletClass(String servletClass);
    public String[] getServletMethods();
    public Servlet getServlet();
    public void setServlet(Servlet servlet);
}
