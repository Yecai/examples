package com.xiaopan.thread.artbook._5_lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class _1_LockUseCase {
	public static void main(String[] args) {
		Lock lock = new ReentrantLock();
		lock.lock();
		try {
			
		} finally {
			//��finally�����ͷ�����Ŀ���Ǳ�֤�ڻ�ȡ����֮�������ܹ����ͷ�
			lock.unlock();
		}
	}
}
