package com.xiaopan.nio;

import java.io.IOException;
import java.util.Scanner;

public class Test {
    @SuppressWarnings("resource")
    public static void main(String[] args) throws InterruptedException, IOException {
        Server.start();
        
        Thread.sleep(100);
        
        Client.start();
        
        //控制台输入算式
        while(Client.sendMsg(new Scanner(System.in).nextLine()));
    }
}
