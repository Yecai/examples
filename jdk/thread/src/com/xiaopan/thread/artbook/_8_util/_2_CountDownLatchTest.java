package com.xiaopan.thread.artbook._8_util;

import java.util.concurrent.CountDownLatch;

public class _2_CountDownLatchTest {
    static CountDownLatch c = new CountDownLatch(2);
    
    public static void main(String[] args) throws Exception {
        new Thread(()->{
            System.out.println(1);
            c.countDown();
            System.out.println(2);
            c.countDown();
        }).start();
        c.await();
        System.out.println(3);
    }
}   
