package com.xiaopan.thread.base;

public class _1_yield {
    //使当前线程从执行状态（运行状态）变为可执行态（就绪状态）
    /**
        sleep执行后线程进入阻塞状态
        yield执行后线程进入就绪状态
        join执行后线程进入阻塞状态
     */
    public static void main(String[] args) {
        Thread producer = new Producer();
        Thread consumer = new Consumer();
        
        producer.setPriority(Thread.MIN_PRIORITY); //Min Priority
        consumer.setPriority(Thread.MAX_PRIORITY); //Max Priority
        
        producer.start();
        consumer.start();
    }
}

class Producer extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            System.out.println("producer count " + i );
            Thread.yield(); //对比加yield与不加yield结果
        }
    }
}

class Consumer extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            System.out.println("consumer count " + i );
            Thread.yield(); //对比加yield与不加yield结果
        }
    }
}
