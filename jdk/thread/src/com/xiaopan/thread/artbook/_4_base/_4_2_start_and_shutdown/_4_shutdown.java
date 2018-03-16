package com.xiaopan.thread.artbook._4_base._4_2_start_and_shutdown;

import java.util.concurrent.TimeUnit;
/**
 * @Package: com.xiaopan.thread.artbook._4_base
 * @ClassName: _4_shutdown
 * @date: 2017年8月22日 下午4:24:55
 * @Description: 结束线程
 * 1、中断interrupt
 * 2、标识位cancel()
 * 通过标识位或者中断操作的方式能够使线程在终止时有机会去清理资源，而不是武断地
将线程停止，因此这种终止线程的做法显得更加安全和优雅
 */
public class _4_shutdown {
	public static void main(String[] args) throws InterruptedException {
		Runner one = new Runner();
		Thread countThread = new Thread(one, "CountThread");
		countThread.start();
		//睡眠1秒，main线程对CountThread进行中断，使CountThread能够感知中断而结束
		TimeUnit.SECONDS.sleep(1);
		countThread.interrupt();
		
		Runner two = new Runner();
		countThread = new Thread(two, "CountThread");
		countThread.start();
		//睡眠1秒，mian线程对Runner two进行取消，使CountThread能够感知on为false而结束
		TimeUnit.SECONDS.sleep(1);
		two.cancel();
	}
	
	private static class Runner implements Runnable {
		
		private long i;
		private volatile boolean on = true;
		
		@Override
		public void run() {
			while(on && !Thread.currentThread().isInterrupted()) {
				i++;
			}
			System.out.println("线程安全退出，Count i = " + i);
		}
		public void cancel() {
			on = false;
		}
	}
}
