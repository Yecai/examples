package com.xiaopan.thread.artbook._5_lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;

public interface _2_Lock {

    /**
     * 获得锁
     *
     * <p>如果锁不可用，则当前线程将被禁用以进行线程调度，并处于休眠状态，直到获取锁。
     *
     * <p><b>实施注意事项</b>
     *
     * <p>Lock实现应该能够检测到锁的错误使用，例如导致死锁的调用，并且在这种情况下抛出（未检查）异常。 
     * 异常和异常场景必须在锁实现中描述清楚。
     */
    void lock();

    /**
     * 获取锁定，除非当前线程被中断
     *
     * <p>获取锁定（如果可用），并立即返回
     *
     * <p>如果锁不可用，那么当前线程将被禁用以进行线程调度，并且处于休眠状态，直到发生以下两件事情中的一个：
     *
     * <ul>
     * <li>当前线程获得锁; 或者
     * <li>其他某个线程中断当前线程，并且支持对锁获取的中断。
     * </ul>
     *
     * <p>如果当前线程:
     * <ul>
     * <li>在进入此方法时设置了其中断状态; 或者
     * <li>在获取锁时被中断，并且支持对锁获取的中断,则抛出InterruptedException，并清除当前线程的中断状态。
     * </ul>
     * 
     * <p><b>实施注意事项</b>
     *
     * <p>在某些实现中中断锁获取的能力可能是不可能的，如果可能的话可能是昂贵的操作。 程序员应该意识到，这可能是这种情况。 在这种情况下，实施应该记录。
     *
     * <p>一个实现可以有利于通过正常的方法返回来响应中断.
     *
     * <p>Lock实现可能能够检测锁的错误使用，例如将导致死锁的调用，并且可能在这种情况下抛出（未检查）异常。 异常环境和异常类型必须通过该Lock实现记录在案。
     *
     * @throws InterruptedException 如果当前线程在获取锁定时中断（并且支持锁定获取的中断）
     */
    void lockInterruptibly() throws InterruptedException;

    /**
     * 当且仅当锁空闲时获取到锁
     *
     * <p>获取锁，如果锁当前可用，则获取锁并立即返回true。
     * 如果锁不可用，则立即返回false。
     *
     * <p>使用方式:
     *  <pre> {@code
     * Lock lock = ...;
     * if (lock.tryLock()) {
     *   try {
     *     // 执行受保护状态
     *   } finally {
     *     lock.unlock();
     *   }
     * } else {
     *   // 执行替代行动
     * }}</pre>
     *
     * 此用法可确保获取到锁时锁已被之前线程取消，不要在未获取的锁定的情况下尝试unlock解锁。
     *
     * @return {@code true} 获取到了锁
     *         {@code false} 其他情况
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
