package com.ldl.user;

import com.ldl.server.core.Request;
import com.ldl.server.core.Response;
import com.ldl.server.core.Servlet;

public class LoginServlet implements Servlet {
    @Override
    public void service(Request request, Response response) {
        response.print("<html>");
        response.print("<head>");
        response.print("<meta charset=\"UTF-8\">");
        response.print("<title>");
        response.print("登陆");
        response.print("</title>");
        response.print("</head>");
        response.print("<body>");
        response.print("登陆成功，" + request.getParameterValue("uname"));
        response.print("</body>");
        response.print("</html>");
    }
}
