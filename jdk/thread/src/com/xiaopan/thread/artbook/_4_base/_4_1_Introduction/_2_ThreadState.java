package com.xiaopan.thread.artbook._4_base._4_1_Introduction;

/**
 * @Package: com.xiaopan.thread.artbook._4_base
 * @ClassName: _2_ThreadState
 * @date: 2017年8月22日 下午3:32:58
 * @Description: Java线程的状态
 * 运行该示例，打开终端或者命令提示符，键入“jps”
 * 可以看到运行示例对应的进程ID
 * 接着再键入“jstack ID”
 */
public class _2_ThreadState {
	public static void main(String[] args) {
		new Thread(new TimeWaiting(), "TimeWaitingThread").start();
		new Thread(new Waiting(), "WaitingThread").start();
		//使用两个Blocked线程，一个获取锁成功，另一个被阻塞
		new Thread(new Blocked(), "BlockedThread-1").start();
		new Thread(new Blocked(), "BlockedThread-2").start();
	}
	
	//该线程不断地进行睡眠
	static class TimeWaiting implements Runnable {
		@Override
		public void run() {
			while(true) {
				_2_SleepUtils.second(100);
			}
		}
	}
	
	//该线程在Waiting.class实例上等待
	static class Waiting implements Runnable {
		@Override
		public void run() {
			while(true) {
				synchronized (Waiting.class) {
					try {
						Waiting.class.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	//该线程在Bolcked.class实例上加锁后，不会释放该锁
	static class Blocked implements Runnable {

		@Override
		public void run() {
			synchronized (Blocked.class) {
				while(true) {
					_2_SleepUtils.second(100);
				}
			}
		}
		
	}
}
