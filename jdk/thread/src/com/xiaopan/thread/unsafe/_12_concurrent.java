package com.xiaopan.thread.unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

import sun.misc.Unsafe;

/**
 * @Package: com.xiaopan.thread.unsafe
 * @ClassName: _12_concurrent
 * @date: 2018年3月19日 下午9:41:08
 * @Description: 并发
 * 关于并发编程使用Unsafe的只言片语。compareAndSwap 方法是原子的，
 * 可以用来实现高性能的无锁化数据结构。
 * 举个例子，多个线程并发的更新共享的对象这种场景：
 */
public class _12_concurrent {
    public static void main(String[] args) throws Exception {
        int NUM_OF_THREADS = 1000;
        int NUM_OF_INCREMENTS = 100000;
        ExecutorService service = Executors.newFixedThreadPool(NUM_OF_THREADS);
//        Counter counter = new StupidCounter(); // 使用普通无并发处理的counter
//        Counter counter = new SyncCounter(); // 使用synchronized实现同步的counter
//        Counter counter = new LockCounter(); // 使用锁实现的counter
//        Counter counter = new AtomicCounter(); // 使用原子类实现的counter
        Counter counter = new CASCounter(); // 使用Unsafe的原子方法compareAndSwapLong实现的counter
        
        long before = System.currentTimeMillis();
        for (int i = 0; i < NUM_OF_THREADS; i++) {
            service.submit(new CounterClient(counter, NUM_OF_INCREMENTS));
        }
        service.shutdown();
        service.awaitTermination(1, TimeUnit.MINUTES);
        long after = System.currentTimeMillis();
        System.out.println("Counter result: " + counter.getCounter());
        System.out.println("Time passed in ms:" + (after - before));
    }
}

interface Counter {
    void increment();
    long getCounter();
}
/** 无并发控制的实现 **/
class StupidCounter implements Counter {
    private long counter = 0;

    @Override
    public void increment() {
        counter++;
    }

    @Override
    public long getCounter() {
        return counter;
    }
}
/**使用同步synchronized实现**/
class SyncCounter implements Counter {
    private long counter = 0;

    @Override
    public synchronized void increment() {
        counter++;
    }

    @Override
    public long getCounter() {
        return counter;
    }
}
/**使用锁实现**/
class LockCounter implements Counter {
    private long counter = 0;
    private WriteLock lock = new ReentrantReadWriteLock().writeLock();

    @Override
    public void increment() {
        lock.lock();
        counter++;
        lock.unlock();
    }

    @Override
    public long getCounter() {
        return counter;
    }
}
/**使用原子类实现**/
class AtomicCounter implements Counter {
    AtomicLong counter = new AtomicLong(0);

    @Override
    public void increment() {
        counter.incrementAndGet();
    }

    @Override
    public long getCounter() {
        return counter.get();
    }
}
/**使用Unsafe的原子方法compareAndSwapLong实现**/
class CASCounter implements Counter {
    private volatile long counter = 0;
    private Unsafe unsafe;
    private long offset;

    public CASCounter() throws Exception {
        unsafe = getUnsafe();
        offset = unsafe.objectFieldOffset(CASCounter.class.getDeclaredField("counter"));
    }

    @Override
    public void increment() {
        long before = counter;
        while (!unsafe.compareAndSwapLong(this, offset, before, before + 1)) {
            before = counter;
        }
    }

    @Override
    public long getCounter() {
        return counter;
    }
    public Unsafe getUnsafe() throws Exception {
        // 通过反射得到theUnsafe对应的Field对象
        Field field = Unsafe.class.getDeclaredField("theUnsafe");
        // 设置该Field为可访问
        field.setAccessible(true);
        // 通过Field得到该Field对应的具体对象，传入null是因为该Field为static的
        return (Unsafe) field.get(null);
    }
}
class CounterClient implements Runnable {
    private Counter c;
    private int num;

    public CounterClient(Counter c, int num) {
        this.c = c;
        this.num = num;
    }

    @Override
    public void run() {
        for (int i = 0; i < num; i++) {
            c.increment();
        }
    }
}