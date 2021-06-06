package com.ldl.user;

import com.ldl.server.connector.Request;
import com.ldl.server.connector.Response;
import com.ldl.server.Servlet;

public class OtherServlet implements Servlet {
    @Override
    public void service(Request request, Response response) {
        response.print("处理其他请求");
    }

    @Override
    public void destroy() {

    }
}
