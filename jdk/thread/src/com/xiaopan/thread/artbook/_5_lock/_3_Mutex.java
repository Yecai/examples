package com.xiaopan.thread.artbook._5_lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class _3_Mutex implements Lock {
    // ��̬�ڲ��࣬�Զ���ͬ����
    @SuppressWarnings("serial")
    private static class Sync extends AbstractQueuedSynchronizer {
        // �Ƿ���ռ��״̬
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        // ��״̬Ϊ0��ʱ���ȡ��
        public boolean tryAcquire(int acquires) {
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        // �ͷ�������״̬����Ϊ0
        protected boolean tryRelease(int releases) {
            if (getState() == 0)
                throw new IllegalMonitorStateException();
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        // ����һ��Condition��ÿ��condition��������һ��condition����
        Condition newCondition() {
            return new ConditionObject();
        }
    }

    // ����Ҫ����������Sync�ϼ���
    private final Sync sync = new Sync();

    public void lock() {
        sync.acquire(1);
    }

    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    public void unlock() {
        sync.release(1);
    }

    public Condition newCondition() {
        return sync.newCondition();
    }

    public boolean isLocked() {
        return sync.isHeldExclusively();
    }

    public boolean hasQueuedThreads() {
        return sync.hasQueuedThreads();
    }

    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    public boolean tryLock(long timeout, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(timeout));
    }
}
