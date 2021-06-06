package com.ldl.server;

public class BootStrap {
    public static void main(String[] args) {
//        Server server = new Server();
//        server.start();
//        server.receive();

        Service service = new StandardService();
        service.start();
    }
}
