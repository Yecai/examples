package com.xiaopan.thread.artbook._4_base._4_1_Introduction;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Package: com.xiaopan.thread.artbook._4_base
 * @ClassName: _1_Priority
 * @date: 2017年8月22日 下午3:32:00
 * @Description: 线程优先级
 */
public class _1_Priority {
	
	private static volatile boolean notStart = true;
	private static volatile boolean notEnd = true;
	
	public static void main(String[] args) throws InterruptedException {
		List<Job> jobs = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			int priority = i < 5 ? Thread.MIN_PRIORITY : Thread.MAX_PRIORITY;
			Job job = new Job(priority);
			jobs.add(job);
			Thread thread = new Thread(job, "Thread:" + i);
			thread.setPriority(priority);
			thread.start();
		}
		notStart = false;
		TimeUnit.SECONDS.sleep(5);
		notEnd = false;
		for (Job job : jobs) {
			System.out.println("Job Priority:" + job.priority + ", Count:" + job.jobCount);
		}
	}
	
	
	static class Job implements Runnable {

		private int priority;
		private long jobCount;
		
		public Job(int priority) {
			this.priority = priority;
		}
		
		@Override
		public void run() {
			while (notStart) {
				Thread.yield();
			}
			while (notEnd) {
				Thread.yield();
				jobCount++;
			}
		}
		
	}
}
