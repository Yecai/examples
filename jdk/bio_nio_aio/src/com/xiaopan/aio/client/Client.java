package com.xiaopan.aio.client;

import java.util.Scanner;

public class Client {
    private static String DEFAULT_HOST = "127.0.0.1";
    private static int DEFAULT_PORT = 12345;
    private static AsyncClientHandler clientHandler;
    
    public static void start() {
        start(DEFAULT_HOST, DEFAULT_PORT);
    }

    private static void start(String host, int port) {
        if (clientHandler != null) {
            return;
        }
        clientHandler = new AsyncClientHandler(host, port);
        new Thread(clientHandler, "Client").start();
    }

    public static boolean sendMsg(String msg) {
        if ("q".equals(msg)) {
            return false;
        }
        clientHandler.sendMsg(msg);
        return true;
    }
    
    @SuppressWarnings("resource")
    public static void main(String[] args) {
        Client.start();
        System.out.println("请输入请求信息：");
        Scanner scanner = new Scanner(System.in);
        while(Client.sendMsg(scanner.nextLine()));
        
    }
}
