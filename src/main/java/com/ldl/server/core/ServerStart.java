package com.ldl.server.core;

public class ServerStart {
    public static void main(String[] args) {
        Server server = new Server();
        server.start();
        server.receive();
    }
}
