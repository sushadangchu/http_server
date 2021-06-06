package com.ldl.server.container;

import com.ldl.server.Service;

/**
 * @Author ldl
 * @Date 2021/6/4 22:47
 */
public interface Engine extends Container{
    public String getDefaultHost();
    public void setDefaultHost(String host);
    public Service getService();
    public void setService(Service service);
}
