package com.xiaopan.thread.artbook._8_util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import com.xiaopan.thread.artbook._4_base._4_1_Introduction.SleepUtils;

/**
 * @Package: com.xiaopan.thread.artbook._8_util
 * @ClassName: _6_SemaphoreTest
 * @date: 2018年3月21日 下午2:24:30
 * @Description:
 * Semaphore（信号量）是用来控制同时访问特定资源的线程数量，它通过协调各个线程，以
 * 保证合理的使用公共资源。
 * Semaphore可以用于做流量控制，特别是公用资源有限的应用场景
 * 
 * 在代码中，虽然有30个线程在执行，但是只允许10个并发执行。Semaphore的构造方法
 * Semaphore（int permits）接受一个整型的数字，表示可用的许可证数量。Semaphore（10）表示允
 * 许10个线程获取许可证，也就是最大并发数是10。Semaphore的用法也很简单，首先线程使用
 * Semaphore的acquire()方法获取一个许可证，使用完之后调用release()方法归还许可证。还可以
 * 用tryAcquire()方法尝试获取许可证。
 */
public class _6_SemaphoreTest {
    private static final int THREAD_COUNT = 30;
    private static ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_COUNT);
    
    private static Semaphore s = new Semaphore(10);
    public static void main(String[] args) {
        for (int i = 0; i < THREAD_COUNT; i++) {
            threadPool.execute(() -> {
                try {
                    s.acquire();
                    SleepUtils.second(2);
                    System.out.println(Thread.currentThread().getName() + ":save data");
                    s.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        threadPool.shutdown();
    }
}
