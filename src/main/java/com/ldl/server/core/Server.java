package com.ldl.server.core;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;
    private boolean isRunning;
    public static final String BLANK = " ";
    public static final String CRLF = "\r\n";
    public static final String HTTP_VERSION = "HTTP/1.1";
    public static final int STATUS_OK = 200;

    public void start() {
        System.out.println("启动服务");
        try {
            serverSocket = new ServerSocket(8888);
        } catch (IOException e) {
            e.printStackTrace();
            stop();
            System.out.println("服务器启动失败！");
        }
        isRunning = true;
        System.out.println("服务器启动成功！");
    }

    public void receive() {
        while(isRunning) {
            try {
                Socket socket = serverSocket.accept();
                new Thread(new Dispatcher(socket)).start();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("服务器接收失败");
            }
        }
    }

    public void stop() {
        isRunning = false;
        try {
            serverSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
