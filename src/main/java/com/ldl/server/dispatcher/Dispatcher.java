package com.ldl.server.dispatcher;

import com.ldl.server.WebApp;
import com.ldl.server.connector.Request;
import com.ldl.server.connector.Response;
import com.ldl.server.Servlet;
import com.ldl.server.util.ioUtil;

import java.io.IOException;
import java.net.Socket;

public class Dispatcher implements Runnable {

    private Socket socket;
    private Request request;
    private Response response;

    public Dispatcher() {}

    public Dispatcher(Socket socket) throws IOException {
        this.socket = socket;
        request = new Request(socket);
        response = new Response(socket);
    }
    @Override
    public void run() {
        try {
            if (request.getUri() == null || "".equals(request.getUri())) {
                response.print(new String(ioUtil.readAllBytes("/index.html")));
                response.push(200);
                release();
                return;
            }
            Servlet servlet = WebApp.getServletFromUri(request.getUri());
            if (servlet != null) {
                servlet.service(request, response);
                response.push(200);
            } else {
                response.print(new String(ioUtil.readAllBytes("/error.html")));
                response.push(404);
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                response.print("服务器异常");
                response.push(500);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        release();
    }

    private void release() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
