package com.xiaopan.thread.artbook._8_util;

import java.util.concurrent.CyclicBarrier;

/**
 * @Package: com.xiaopan.thread.artbook._8_util
 * @ClassName: _4_CyclicBarrierTest2
 * @date: 2018年3月21日 下午2:14:02
 * @Description: 
 * CyclicBarrier还提供一个更高级的构造函数CyclicBarrier（int parties，Runnable barrier-
 * Action），用于在线程到达屏障时，优先执行barrierAction，方便处理更复杂的业务场景
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
