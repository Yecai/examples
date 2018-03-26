package com.xiaopan.thread.artbook._8_util;

import java.util.concurrent.CyclicBarrier;

/**
 * @Package: com.xiaopan.thread.artbook._8_util
 * @ClassName: _2_CyclicBarrierTest
 * @date: 2018年3月21日 下午1:55:00
 * @Description: 
 * CyclicBarrier的字面意思是可循环使用（Cyclic）的屏障（Barrier）。它要做的事情是，让一
 * 组线程到达一个屏障（也可以叫同步点）时被阻塞，直到最后一个线程到达屏障时，屏障才会
 * 开门，所有被屏障拦截的线程才会继续运行。
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
