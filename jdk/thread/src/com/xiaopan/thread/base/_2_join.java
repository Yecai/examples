package com.xiaopan.thread.base;

public class _2_join {
    //使得一个线程在另一个线程结束后再执行
    
    public static void main(String[] args) {
        Thread producer = new Producer();
        
        System.out.println("start");
        producer.start();
        try {
            producer.join(); //producer阻塞，主线程等待producer执行完成（可以注释查看不加的效果）
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end"); //producer全部执行完成后才执行
    }
}

