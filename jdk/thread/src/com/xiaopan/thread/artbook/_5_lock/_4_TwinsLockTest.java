package com.xiaopan.thread.artbook._5_lock;

import java.util.concurrent.locks.Lock;

import com.xiaopan.thread.artbook._4_base._4_1_Introduction.SleepUtils;

public class _4_TwinsLockTest {
    
    public static void main(String[] args) throws InterruptedException {
        final Lock lock = new _4_TwinsLock();
        class Worker extends Thread {
            @Override
            public void run() {
                while (true) {
                    lock.lock();
                    try {
                        SleepUtils.second(1);
                        System.out.println(Thread.currentThread().getName());
                        SleepUtils.second(1);
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }
        
        //启动10个线程
        for (int i = 0; i < 10; i++) {
            Worker w = new Worker();
            w.setDaemon(true);
            w.start();
        }
        //每隔1秒换行
        for (int i = 0; i < 10; i++) {
            SleepUtils.second(1);
            System.out.println(1);
        }
    }
}


