package com.xiaopan.aio.server;

public class Server {
    public static int DEFAULT_PORT = 12345;
    public static AsyncServerHandler serverHandler;
    public volatile static long clientCount = 0;
    
    public static void start() {
        start(DEFAULT_PORT);
    }
    
    public static synchronized void start(int port) {
        if (serverHandler != null) {
            return;
        }
        serverHandler = new AsyncServerHandler(port);
        new Thread(serverHandler, "Server").start();
    }
    
    public static void main(String[] args) {
        Server.start();
    }
}
