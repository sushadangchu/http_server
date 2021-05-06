package com.ldl.server.core;

import java.io.IOException;
import java.io.InputStream;
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
                InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("index.html");
                response.print(new String(inputStream.readAllBytes()));
                response.push(200);
                return;
            }
            Servlet servlet = WebApp.getServletFromUri(request.getUri());
            if (servlet != null) {
                servlet.service(request, response);
                response.push(200);
            } else {
                InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("error.html");
                response.print(new String(inputStream.readAllBytes()));
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
