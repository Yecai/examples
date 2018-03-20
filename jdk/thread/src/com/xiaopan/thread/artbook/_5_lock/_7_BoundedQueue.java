package com.xiaopan.thread.artbook._5_lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Package: com.xiaopan.thread.artbook._5_lock
 * @ClassName: _7_BoundedQueue
 * @date: 2018��3��20�� ����4:16:53
 * @Description: 
 * �н������һ������Ķ��У�������Ϊ��ʱ�����еĻ�ȡ����
 * ����������ȡ�̣߳�ֱ��������������Ԫ�أ�����������ʱ�����еĲ��������������������
 * �̣�ֱ�����г��֡���λ��
 */
public class _7_BoundedQueue<T> {
    private Object[] items;
    //��ӵ��±꣬ɾ�����±�����鵱ǰ����
    private int addIndex,  removeIndex, count;
    private Lock lock = new ReentrantLock();
    private Condition notEmpty = lock.newCondition();
    private Condition notFull = lock.newCondition();
    
    public _7_BoundedQueue(int size) {
        items = new Object[size];
    }
    
    //���һ��Ԫ�أ������������������߳̽���ȴ�״̬��ֱ���С���λ��
    public void add(T t) throws InterruptedException {
        lock.lock();
        try {
            while (count == items.length) {
                notFull.await();
            }
            items[addIndex] = t;
            if (++addIndex == items.length) {
                addIndex = 0;
            }
            ++count;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }
    
    //��ͷ��ɾ��һ��Ԫ�أ��������գ���ɾ���߳̽���ȴ�״̬��ֱ���������Ԫ��
    @SuppressWarnings("unchecked")
    public T remove() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) {
                notEmpty.await();
            }
            Object x = items[removeIndex];
            if  (++removeIndex == items.length) {
                removeIndex = 0;
            }
            --count;
            notFull.signal();
            return (T)x;
        } finally {
            lock.unlock();
        }
    }
}
