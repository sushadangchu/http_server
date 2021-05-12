package com.ldl.user;

import com.ldl.server.core.Request;
import com.ldl.server.core.Response;
import com.ldl.server.core.Servlet;

public class OtherServlet implements Servlet {
    @Override
    public void service(Request request, Response response) {
        response.print("处理其他请求");
    }
}
