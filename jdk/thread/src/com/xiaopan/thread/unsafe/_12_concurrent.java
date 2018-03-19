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
 * @date: 2018��3��19�� ����9:41:08
 * @Description: ����
 * ���ڲ������ʹ��Unsafe��ֻ��Ƭ�compareAndSwap ������ԭ�ӵģ�
 * ��������ʵ�ָ����ܵ����������ݽṹ��
 * �ٸ����ӣ�����̲߳����ĸ��¹���Ķ������ֳ�����
 */
public class _12_concurrent {
    public static void main(String[] args) throws Exception {
        int NUM_OF_THREADS = 1000;
        int NUM_OF_INCREMENTS = 100000;
        ExecutorService service = Executors.newFixedThreadPool(NUM_OF_THREADS);
//        Counter counter = new StupidCounter(); // ʹ����ͨ�޲��������counter
//        Counter counter = new SyncCounter(); // ʹ��synchronizedʵ��ͬ����counter
//        Counter counter = new LockCounter(); // ʹ����ʵ�ֵ�counter
//        Counter counter = new AtomicCounter(); // ʹ��ԭ����ʵ�ֵ�counter
        Counter counter = new CASCounter(); // ʹ��Unsafe��ԭ�ӷ���compareAndSwapLongʵ�ֵ�counter
        
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
/** �޲������Ƶ�ʵ�� **/
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
/**ʹ��ͬ��synchronizedʵ��**/
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
/**ʹ����ʵ��**/
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
/**ʹ��ԭ����ʵ��**/
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
/**ʹ��Unsafe��ԭ�ӷ���compareAndSwapLongʵ��**/
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
        // ͨ������õ�theUnsafe��Ӧ��Field����
        Field field = Unsafe.class.getDeclaredField("theUnsafe");
        // ���ø�FieldΪ�ɷ���
        field.setAccessible(true);
        // ͨ��Field�õ���Field��Ӧ�ľ�����󣬴���null����Ϊ��FieldΪstatic��
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