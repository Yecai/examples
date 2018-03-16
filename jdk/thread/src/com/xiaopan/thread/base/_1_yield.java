package com.xiaopan.thread.base;

public class _1_yield {
    //ʹ��ǰ�̴߳�ִ��״̬������״̬����Ϊ��ִ��̬������״̬��
    /**
        sleepִ�к��߳̽�������״̬
        yieldִ�к��߳̽������״̬
        joinִ�к��߳̽�������״̬
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
            Thread.yield(); //�Աȼ�yield�벻��yield���
        }
    }
}

class Consumer extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            System.out.println("consumer count " + i );
            Thread.yield(); //�Աȼ�yield�벻��yield���
        }
    }
}
