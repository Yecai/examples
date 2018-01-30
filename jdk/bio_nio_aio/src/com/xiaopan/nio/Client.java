package com.xiaopan.nio;

import java.io.IOException;

public class Client {
    private static String DEFAULT_HOST = "127.0.0.1";
    private static int DEFAULT_PORT = 12345;
    private static ClientHandle clientHandle;
    
    public static void start() {
        start(DEFAULT_HOST, DEFAULT_PORT);
    }
    
    public static synchronized void start(String ip, int port) {
        if (clientHandle != null) {
            clientHandle.stop();
        }
        clientHandle = new ClientHandle(ip, port);
        new Thread(clientHandle, "client").start();
    }
    
    public static boolean sendMsg(String msg) throws IOException {
        if ("q".equals(msg)) {
            return false;
        }
        clientHandle.sendMsg(msg);
        return true;
    }
    
    public static void main(String[] args) {
        start();
    }
}
