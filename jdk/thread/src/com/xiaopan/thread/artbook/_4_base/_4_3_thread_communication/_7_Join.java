package com.xiaopan.thread.artbook._4_base._4_3_thread_communication;

import java.util.concurrent.TimeUnit;

/**
 * @Package: com.xiaopan.thread.artbook._4_base
 * @ClassName: _7_Join
 * @date: 2017��8��22�� ����6:13:29
 * @Description: ���һ���߳�Aִ����thread.join()��䣬�京���ǣ���ǰ�߳�A�ȴ�thread�߳���ֹ֮���
��thread.join()����
 */
public class _7_Join {
	//������10���̣߳����0~9��ÿ���̵߳���ǰһ���̵߳�join()������Ҳ�����߳�0�����ˣ��߳�1���ܴ�join()�����з��أ����߳�0��Ҫ�ȴ�main�߳̽�����
	public static void main(String[] args) throws InterruptedException {
		Thread previous = Thread.currentThread();
		for (int i = 0; i < 10; i++) {
			//ÿ���߳�ӵ��ǰһ���̵߳����ã���Ҫ�ȴ�ǰһ���߳���ֹ�����ܴӵȴ��з���
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

