package com.ldl.user;

import com.ldl.server.connector.Request;
import com.ldl.server.connector.Response;
import com.ldl.server.Servlet;

public class RegisterServlet implements Servlet {

    @Override
    public void service(Request request, Response response) {
        String uname = request.getParameterValue("uname");
        String[] favs = request.getParameterValues("fav");
        String fav = "";
        for (String str : favs) {
            if ("0".equals(str)) {
                fav += "跑步";
            } else if("1".equals(str)) {
                fav += "游泳";
            } else if("2".equals(str)) {
                fav += "读书";
            }
        }
        response.print("<html>");
        response.print("<head>");
        response.print("<meta charset=\"UTF-8\">");
        response.print("<title>");
        response.print("登陆");
        response.print("</title>");
        response.print("</head>");
        response.print("<body>");
        response.print("登陆名:" + uname + ",爱好:" + fav);
        response.print("</body>");
        response.print("</html>");
    }

    @Override
    public void destroy() {

    }
}
