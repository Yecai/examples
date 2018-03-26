package com.xiaopan.thread.artbook._8_util;

import java.util.concurrent.CyclicBarrier;

/**
 * @Package: com.xiaopan.thread.artbook._8_util
 * @ClassName: _5_CyclicBarrireTest3
 * @date: 2018年3月21日 下午2:19:12
 * @Description: 
 * CountDownLatch的计数器只能使用一次，而CyclicBarrier的计数器可以使用reset()方法重
 * 置。所以CyclicBarrier能处理更为复杂的业务场景。例如，如果计算发生错误，可以重置计数
 * ，并让线程重新执行一次。
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
