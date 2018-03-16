package com.xiaopan.thread.artbook._5_lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class _1_LockUseCase {
	public static void main(String[] args) {
		Lock lock = new ReentrantLock();
		lock.lock();
		try {
			
		} finally {
			//在finally块中释放锁，目的是保证在获取到锁之后，最终能够被释放
			lock.unlock();
		}
	}
}
