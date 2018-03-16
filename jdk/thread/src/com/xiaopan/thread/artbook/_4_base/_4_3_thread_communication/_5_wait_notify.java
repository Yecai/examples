package com.xiaopan.thread.artbook._4_base._4_3_thread_communication;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.xiaopan.thread.artbook._4_base._4_1_Introduction._2_SleepUtils;

/**
 * @Package: com.xiaopan.thread.artbook._4_base
 * @ClassName: _5_wait_notify
 * @date: 2017年8月22日 下午5:10:06
 * @Description: 等待与通知
 * wait释放锁
 * notify唤醒wait的线程，重新参与竞争锁（线程状态由WAITING变为BLOCKED）
 * 由于锁竞争，“hold lock again” 与 “notifyed”日志顺序可能会不一致
 * 1）使用wait()、notify()和notifyAll()时需要先对调用对象加锁。
 * 2）调用wait()方法后，线程状态由RUNNING变为WAITING，并将当前线程放置到对象的等待队列。
 * 3）notify()或notifyAll()方法调用后，等待线程依旧不会从wait()返回，需要调用notify()或notifAll()的线程释放锁之后，等待线程才有机会从wait()返回。
 * 4）notify()方法将等待队列中的一个等待线程从等待队列中移到同步队列中，而notifyAll()方法则是将等待队列中所有的线程全部移到同步队列，被移动的线程状态由WAITING变为BLOCKED。
 * 5）从wait()方法返回的前提是获得了调用对象的锁。
 */
public class _5_wait_notify {
	static boolean flag = true;
	static Object lock = new Object();
	
	public static void main(String[] args) throws InterruptedException {
		Thread waitThread = new Thread(new Wait(), "WaitThread");
		waitThread.start();
		
		TimeUnit.SECONDS.sleep(1);
		
		Thread notifyThread = new  Thread(new Notify(), "NotifyThread");
		notifyThread.start();
	}
	
	static class Wait implements Runnable {
		@Override
		public void run() {
			//加锁 ，拥有lock的Monitor
			synchronized (lock) {
				//当条件不满足时，继续wait，同时释放了lock的锁
				while(flag) {
					try {
						System.out.println(Thread.currentThread() + " flag is true. wait @ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
						lock.wait(); //wait释放了lock锁，notify线程获得了锁后开始执行
						//notify后，重新获取到了lock锁，继续执行
						System.out.println(Thread.currentThread() + " notifyed. running @ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
					} catch (InterruptedException e) {
						
					}
				}
				//条件满足时，完成工作
				System.out.println(Thread.currentThread() + " flag is false. running @ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
			}
		}
	}
	
	static class Notify implements Runnable {
		@Override
		public void run() {
			//加锁，拥有lock的Monitor
			synchronized (lock) {
				//获取lock的锁，然后进行通知，通知时不会释放lock的锁
				//直到当前线程释放了lock后，WaitThread才能从wait方法中返回
				System.out.println(Thread.currentThread() + " hold lock. notify @ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
				lock.notifyAll();
				flag = false;
				_2_SleepUtils.second(2);
			}
			//Notify释放了锁
//			_2_SleepUtils.second(2);
			
			//再次加锁(此时，Notify与Wait同时竞争锁）
			synchronized (lock) {
				System.out.println(Thread.currentThread() + " hold lock again. sleep @ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
				_2_SleepUtils.second(5);
			}
		}
	}
}
