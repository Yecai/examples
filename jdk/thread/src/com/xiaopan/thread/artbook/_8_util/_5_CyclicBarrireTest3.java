package com.xiaopan.thread.artbook._8_util;

import java.util.concurrent.CyclicBarrier;

/**
 * @Package: com.xiaopan.thread.artbook._8_util
 * @ClassName: _5_CyclicBarrireTest3
 * @date: 2018��3��21�� ����2:19:12
 * @Description: 
 * CountDownLatch�ļ�����ֻ��ʹ��һ�Σ���CyclicBarrier�ļ���������ʹ��reset()������
 * �á�����CyclicBarrier�ܴ����Ϊ���ӵ�ҵ�񳡾������磬������㷢�����󣬿������ü���
 * �������߳�����ִ��һ�Ρ�
 */
public class _5_CyclicBarrireTest3 {
    static CyclicBarrier c = new CyclicBarrier(2);
    
    public static void main(String[] args) {
        Thread thread = new Thread(()->{
            try {
                c.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.start();
        thread.interrupt();
        try {
            c.await();
        } catch (Exception e) {
            System.out.println(c.isBroken());
        }
        
        
    }
}
