package com.ldl.server;

import com.ldl.server.connector.Request;
import com.ldl.server.connector.Response;

public interface Servlet {
    public void service(Request request, Response response);
    public void destroy();
}
