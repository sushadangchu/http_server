package com.ldl.server.connector;

import com.ldl.server.Service;
import com.ldl.server.container.LifeCycle;
import com.ldl.server.container.LifeCycleBase;
import com.ldl.server.dispatcher.Dispatcher;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author ldl
 * @Date 2021/6/4 22:59
 */
public class Connector extends LifeCycleBase {

    private ServerSocket serverSocket;
    private boolean isRunning;
    private com.ldl.server.connector.nio.Connector connector;

    public Service service = null;

    public void setService(Service service) {
        this.service = service;
    }

    public void pause() {

    }

    @Override
    protected void initInternal() {

    }

    @Override
    protected void startInternal() {
        System.out.println("start connector");
        try {
            serverSocket = new ServerSocket(8888);
        } catch (IOException e) {
            e.printStackTrace();
            stop();
            System.out.println("Connector start failed");
        }
        isRunning = true;
        connector = new com.ldl.server.connector.nio.Connector();
        receive();
    }

    @Override
    protected void stopInternal() {
        isRunning = false;
        try {
            serverSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void destroyInternal() {

    }

    protected void receive() {
        while(isRunning) {
            try {
                Socket socket = serverSocket.accept();
                connector.getPoolExecutor().execute(new Dispatcher(socket));
                //new Thread(new Dispatcher(socket)).start();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("服务器接收失败");
            }
        }
    }
}
