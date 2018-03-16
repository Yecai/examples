package com.xiaopan.thread.artbook._5_lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;

public interface _2_Lock {

    /**
     * �����
     *
     * <p>����������ã���ǰ�߳̽��������Խ����̵߳��ȣ�����������״̬��ֱ����ȡ����
     *
     * <p><b>ʵʩע������</b>
     *
     * <p>Lockʵ��Ӧ���ܹ���⵽���Ĵ���ʹ�ã����絼�������ĵ��ã�����������������׳���δ��飩�쳣�� 
     * �쳣���쳣������������ʵ�������������
     */
    void lock();

    /**
     * ��ȡ���������ǵ�ǰ�̱߳��ж�
     *
     * <p>��ȡ������������ã�������������
     *
     * <p>����������ã���ô��ǰ�߳̽��������Խ����̵߳��ȣ����Ҵ�������״̬��ֱ�������������������е�һ����
     *
     * <ul>
     * <li>��ǰ�̻߳����; ����
     * <li>����ĳ���߳��жϵ�ǰ�̣߳�����֧�ֶ�����ȡ���жϡ�
     * </ul>
     *
     * <p>�����ǰ�߳�:
     * <ul>
     * <li>�ڽ���˷���ʱ���������ж�״̬; ����
     * <li>�ڻ�ȡ��ʱ���жϣ�����֧�ֶ�����ȡ���ж�,���׳�InterruptedException���������ǰ�̵߳��ж�״̬��
     * </ul>
     * 
     * <p><b>ʵʩע������</b>
     *
     * <p>��ĳЩʵ�����ж�����ȡ�����������ǲ����ܵģ�������ܵĻ������ǰ���Ĳ����� ����ԱӦ����ʶ�������������������� ����������£�ʵʩӦ�ü�¼��
     *
     * <p>һ��ʵ�ֿ���������ͨ�������ķ�����������Ӧ�ж�.
     *
     * <p>Lockʵ�ֿ����ܹ�������Ĵ���ʹ�ã����罫���������ĵ��ã����ҿ���������������׳���δ��飩�쳣�� �쳣�������쳣���ͱ���ͨ����Lockʵ�ּ�¼�ڰ���
     *
     * @throws InterruptedException �����ǰ�߳��ڻ�ȡ����ʱ�жϣ�����֧��������ȡ���жϣ�
     */
    void lockInterruptibly() throws InterruptedException;

    /**
     * ���ҽ���������ʱ��ȡ����
     *
     * <p>��ȡ�����������ǰ���ã����ȡ������������true��
     * ����������ã�����������false��
     *
     * <p>ʹ�÷�ʽ:
     *  <pre> {@code
     * Lock lock = ...;
     * if (lock.tryLock()) {
     *   try {
     *     // ִ���ܱ���״̬
     *   } finally {
     *     lock.unlock();
     *   }
     * } else {
     *   // ִ������ж�
     * }}</pre>
     *
     * ���÷���ȷ����ȡ����ʱ���ѱ�֮ǰ�߳�ȡ������Ҫ��δ��ȡ������������³���unlock������
     *
     * @return {@code true} ��ȡ������
     *         {@code false} �������
     */
    boolean tryLock();

    /**
     * Acquires the lock if it is free within the given waiting time and the
     * current thread has not been {@linkplain Thread#interrupt interrupted}.
     *
     * <p>If the lock is available this method returns immediately
     * with the value {@code true}.
     * If the lock is not available then
     * the current thread becomes disabled for thread scheduling
     * purposes and lies dormant until one of three things happens:
     * <ul>
     * <li>The lock is acquired by the current thread; or
     * <li>Some other thread {@linkplain Thread#interrupt interrupts} the
     * current thread, and interruption of lock acquisition is supported; or
     * <li>The specified waiting time elapses
     * </ul>
     *
     * <p>If the lock is acquired then the value {@code true} is returned.
     *
     * <p>If the current thread:
     * <ul>
     * <li>has its interrupted status set on entry to this method; or
     * <li>is {@linkplain Thread#interrupt interrupted} while acquiring
     * the lock, and interruption of lock acquisition is supported,
     * </ul>
     * then {@link InterruptedException} is thrown and the current thread's
     * interrupted status is cleared.
     *
     * <p>If the specified waiting time elapses then the value {@code false}
     * is returned.
     * If the time is
     * less than or equal to zero, the method will not wait at all.
     *
     * <p><b>Implementation Considerations</b>
     *
     * <p>The ability to interrupt a lock acquisition in some implementations
     * may not be possible, and if possible may
     * be an expensive operation.
     * The programmer should be aware that this may be the case. An
     * implementation should document when this is the case.
     *
     * <p>An implementation can favor responding to an interrupt over normal
     * method return, or reporting a timeout.
     *
     * <p>A {@code Lock} implementation may be able to detect
     * erroneous use of the lock, such as an invocation that would cause
     * deadlock, and may throw an (unchecked) exception in such circumstances.
     * The circumstances and the exception type must be documented by that
     * {@code Lock} implementation.
     *
     * @param time the maximum time to wait for the lock
     * @param unit the time unit of the {@code time} argument
     * @return {@code true} if the lock was acquired and {@code false}
     *         if the waiting time elapsed before the lock was acquired
     *
     * @throws InterruptedException if the current thread is interrupted
     *         while acquiring the lock (and interruption of lock
     *         acquisition is supported)
     */
    boolean tryLock(long time, TimeUnit unit) throws InterruptedException;

    /**
     * Releases the lock.
     *
     * <p><b>Implementation Considerations</b>
     *
     * <p>A {@code Lock} implementation will usually impose
     * restrictions on which thread can release a lock (typically only the
     * holder of the lock can release it) and may throw
     * an (unchecked) exception if the restriction is violated.
     * Any restrictions and the exception
     * type must be documented by that {@code Lock} implementation.
     */
    void unlock();

    /**
     * Returns a new {@link Condition} instance that is bound to this
     * {@code Lock} instance.
     *
     * <p>Before waiting on the condition the lock must be held by the
     * current thread.
     * A call to {@link Condition#await()} will atomically release the lock
     * before waiting and re-acquire the lock before the wait returns.
     *
     * <p><b>Implementation Considerations</b>
     *
     * <p>The exact operation of the {@link Condition} instance depends on
     * the {@code Lock} implementation and must be documented by that
     * implementation.
     *
     * @return A new {@link Condition} instance for this {@code Lock} instance
     * @throws UnsupportedOperationException if this {@code Lock}
     *         implementation does not support conditions
     */
    Condition newCondition();
}
