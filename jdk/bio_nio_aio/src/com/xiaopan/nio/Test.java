package com.xiaopan.nio;

import java.io.IOException;
import java.util.Scanner;

public class Test {
    @SuppressWarnings("resource")
    public static void main(String[] args) throws InterruptedException, IOException {
        Server.start();
        
        Thread.sleep(100);
        
        Client.start();
        
        //����̨������ʽ
        while(Client.sendMsg(new Scanner(System.in).nextLine()));
    }
}
