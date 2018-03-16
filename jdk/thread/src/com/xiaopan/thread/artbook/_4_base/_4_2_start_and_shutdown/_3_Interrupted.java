package com.xiaopan.thread.artbook._4_base._4_2_start_and_shutdown;

import java.util.concurrent.TimeUnit;

import com.xiaopan.thread.artbook._4_base._4_1_Introduction._2_SleepUtils;

public class _3_Interrupted {
	public static void main(String[] args) throws InterruptedException {
		//sleepThread不停的尝试睡眠
		Thread sleepThread = new Thread(new SleepRunner(), "SleepThread");
		sleepThread.setDaemon(true);
		//busyThread不停的运行
		Thread busyThread = new Thread(new BusyRunner(), "BusyThread");
		busyThread.setDaemon(true);
		
		sleepThread.start();
		busyThread.start();
		
		//休眠5秒，让sleepThread和busyThread充分运行
		TimeUnit.SECONDS.sleep(5);
		
		sleepThread.interrupt();
		busyThread.interrupt();
		
		System.out.println("SleepThread interrupted is " + sleepThread.isInterrupted());
		System.out.println("busyThread interrupted is " + busyThread.isInterrupted());
		
		//防止sleepThread和busyThread立即退出
		_2_SleepUtils.second(2);
	}
	
	static class SleepRunner implements Runnable {
		@Override
		public void run() {
			while(true) {
				_2_SleepUtils.second(10);
			}
		}
	}
	
	static class BusyRunner implements Runnable {
		@Override
		public void run() {
			while(true) {
			}
		}
	}
}
