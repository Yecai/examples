package com.xiaopan.aio;

import java.util.Scanner;

import com.xiaopan.aio.client.Client;
import com.xiaopan.nio.Server;

public class Test {
    @SuppressWarnings("resource")
    public static void main(String[] args) throws InterruptedException {
        Server.start();
        Thread.sleep(100);
        Client.start();
        System.out.println("请输入请求信息：");
        Scanner scanner = new Scanner(System.in);
        while(Client.sendMsg(scanner.nextLine()));
    }
}
