package com.xiaopan.thread.artbook._4_base._4_4_pool;

public interface _10_ThreadPool<Job extends Runnable> {
	//ִ��һ��job����� job��Ҫʵ��Runnable
	void execute(Job job);
	//�ر��̳߳�
	void shutdown();
	//���ӹ������߳�
	void addWorkers(int num);
	//���ٹ������߳�
	void removeWorker(int num);
	//�õ����ڵȴ�ִ�е���������
	int getJobSize();
}
