package com.xiaopan.thread.artbook._5_lock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

import com.xiaopan.thread.artbook._4_base._4_1_Introduction.SleepUtils;

public class _5_FairAndUnfairTest {
    private static Lock fairLock = new ReentrantLock2(true);
    private static Lock unfairLock = new ReentrantLock2(false);
    
    private static CountDownLatch latch = new CountDownLatch(5);
    
    @Test
    public void fair() {
        testLock(fairLock);
        try {
            latch.await();
        } catch (InterruptedException e) {
        }
    }
    
//    @Test
//    public void unfair() {
//        testLock(unfairLock);
//        try {
//            latch.await();
//        } catch (InterruptedException e) {
//        }
//    }
    
    private void testLock(Lock lock) {
        //启动5个job
        for (int i = 0; i < 5; i++) {
            Job job = new Job(lock);
            job.start();
        }
    }
    
    private static class Job extends Thread {
        private ReentrantLock2 lock;
        public Job(Lock lock) {
            this.lock = (ReentrantLock2) lock;
        }
        public void run() {
            //连续2次打印当前的Thread的等待队列中的Thread
            lock.lock();
            try {
                SleepUtils.second(1);
                System.out.println("Lock by [" + Thread.currentThread().getName() + "]" + ". Waiting by [" + lock.getQueuedThreads() + "]");
                SleepUtils.second(1);
                System.out.println("Lock by [" + Thread.currentThread().getName() + "]" + ". Waiting by [" + lock.getQueuedThreads() + "]");
            } finally {
                lock.unlock();
                latch.countDown();
            }
        }
    }
    
    private static class ReentrantLock2 extends ReentrantLock {
        public ReentrantLock2(boolean fair) {
            super(fair);
        }
        @Override
        public Collection<Thread> getQueuedThreads() {
            List<Thread> arrayList = new ArrayList<>(super.getQueuedThreads());
            Collections.reverse(arrayList);
            return arrayList;
        }
    }
 }
