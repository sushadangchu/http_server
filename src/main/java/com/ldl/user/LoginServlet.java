package com.ldl.user;

import com.ldl.server.connector.Request;
import com.ldl.server.connector.Response;
import com.ldl.server.Servlet;

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

    @Override
    public void destroy() {

    }
}
