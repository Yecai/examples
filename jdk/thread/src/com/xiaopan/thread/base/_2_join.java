package com.xiaopan.thread.base;

public class _2_join {
    //ʹ��һ���߳�����һ���߳̽�������ִ��
    
    public static void main(String[] args) {
        Thread producer = new Producer();
        
        System.out.println("start");
        producer.start();
        try {
            producer.join(); //producer���������̵߳ȴ�producerִ����ɣ�����ע�Ͳ鿴���ӵ�Ч����
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end"); //producerȫ��ִ����ɺ��ִ��
    }
}

