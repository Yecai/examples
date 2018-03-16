package com.xiaopan.thread.artbook._4_base._4_3_thread_communication;

import java.util.concurrent.TimeUnit;

/**
 * @Package: com.xiaopan.thread.artbook._4_base
 * @ClassName: _7_Join
 * @date: 2017年8月22日 下午6:13:29
 * @Description: 如果一个线程A执行了thread.join()语句，其含义是：当前线程A等待thread线程终止之后才
从thread.join()返回
 */
public class _7_Join {
	//创建了10个线程，编号0~9，每个线程调用前一个线程的join()方法，也就是线程0结束了，线程1才能从join()方法中返回，而线程0需要等待main线程结束。
	public static void main(String[] args) throws InterruptedException {
		Thread previous = Thread.currentThread();
		for (int i = 0; i < 10; i++) {
			//每个线程拥有前一个线程的引用，需要等待前一个线程终止，才能从等待中返回
			Thread thread = new Thread(new Domino(previous), String.valueOf(i));
			thread.start();
			previous = thread;
		}
		TimeUnit.SECONDS.sleep(5);
		System.out.println(Thread.currentThread().getName() + " terminate.");
	}
	

	static class Domino implements Runnable {
		
		private Thread preThread;
		public Domino(Thread preThread) {
			this.preThread = preThread;
		}
		
		@Override
		public void run() {
			try {
				preThread.join();
			} catch (InterruptedException e) {
			}
			System.out.println(Thread.currentThread().getName() + " terminate.");
		}
	}
}

