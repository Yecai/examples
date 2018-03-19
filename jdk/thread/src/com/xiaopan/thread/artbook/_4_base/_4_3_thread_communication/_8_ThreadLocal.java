package com.xiaopan.thread.artbook._4_base._4_3_thread_communication;

import java.util.concurrent.TimeUnit;

/**
 * @Package: com.xiaopan.thread.artbook._4_base._4_3_
 * @ClassName: _8_Profiler
 * @date: 2017年8月22日 下午6:22:39
 * @Description: ThreadLocal
 * 
 */
public class _8_ThreadLocal {
	//第一次get()方法调用时会进行初始化（如果set方法没有调用），每个线程会调用一次
	private static final ThreadLocal<Long> TIME_THREADLOCAL = new ThreadLocal<Long>() {
		protected Long initialValue() {
			return System.currentTimeMillis();
		}
	};
	
	public static final long get() {
		return TIME_THREADLOCAL.get();
	}
	
	public static final void begin() {
		TIME_THREADLOCAL.set(System.currentTimeMillis());
	}
	
	public static final long end() {
		return System.currentTimeMillis() - TIME_THREADLOCAL.get();
	}
	
	public static void main(String[] args) throws InterruptedException {
		System.out.println("init: " + _8_ThreadLocal.get() + "  mills");
		_8_ThreadLocal.begin();
		TimeUnit.SECONDS.sleep(1);
		System.out.println("Cost: " + _8_ThreadLocal.end() + "  mills");
	}
}
