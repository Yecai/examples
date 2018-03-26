package com.xiaopan.thread.artbook._8_util;

import java.util.concurrent.CyclicBarrier;

/**
 * @Package: com.xiaopan.thread.artbook._8_util
 * @ClassName: _4_CyclicBarrierTest2
 * @date: 2018��3��21�� ����2:14:02
 * @Description: 
 * CyclicBarrier���ṩһ�����߼��Ĺ��캯��CyclicBarrier��int parties��Runnable barrier-
 * Action�����������̵߳�������ʱ������ִ��barrierAction�����㴦������ӵ�ҵ�񳡾�
 */
public class _4_CyclicBarrierTest2 {
    static CyclicBarrier c = new CyclicBarrier(2, new A());
    public static void main(String[] args) {
        new Thread(() -> {
            try {
                c.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(1);
        }).start();
        
        try {
            c.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(2);
    }
    static class A implements Runnable {
        @Override
        public void run() {
            System.out.println(3);
        }
    }
}
