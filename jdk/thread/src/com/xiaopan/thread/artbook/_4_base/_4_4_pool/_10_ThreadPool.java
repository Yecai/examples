package com.xiaopan.thread.artbook._4_base._4_4_pool;

public interface _10_ThreadPool<Job extends Runnable> {
	//执行一个job，这个 job需要实现Runnable
	void execute(Job job);
	//关闭线程池
	void shutdown();
	//增加工作者线程
	void addWorkers(int num);
	//减少工作者线程
	void removeWorker(int num);
	//得到正在等待执行的任务数量
	int getJobSize();
}
