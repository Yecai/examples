package com.xiaopan.thread.artbook._8_util;

import java.util.concurrent.CyclicBarrier;

/**
 * @Package: com.xiaopan.thread.artbook._8_util
 * @ClassName: _2_CyclicBarrierTest
 * @date: 2018��3��21�� ����1:55:00
 * @Description: 
 * CyclicBarrier��������˼�ǿ�ѭ��ʹ�ã�Cyclic�������ϣ�Barrier������Ҫ���������ǣ���һ
 * ���̵߳���һ�����ϣ�Ҳ���Խ�ͬ���㣩ʱ��������ֱ�����һ���̵߳�������ʱ�����ϲŻ�
 * ���ţ����б��������ص��̲߳Ż�������С�
 */
public class _3_CyclicBarrierTest {
    static CyclicBarrier c = new CyclicBarrier(2);
    
    public static void main(String[] args) {
        new Thread(()->{
            try {
                c.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(1);
        }).start();
        
        new Thread(()->{
            try {
                c.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(2);
        }).start();
        
    }
}
