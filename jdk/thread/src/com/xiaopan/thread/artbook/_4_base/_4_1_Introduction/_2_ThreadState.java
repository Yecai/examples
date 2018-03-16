package com.xiaopan.thread.artbook._4_base._4_1_Introduction;

/**
 * @Package: com.xiaopan.thread.artbook._4_base
 * @ClassName: _2_ThreadState
 * @date: 2017��8��22�� ����3:32:58
 * @Description: Java�̵߳�״̬
 * ���и�ʾ�������ն˻���������ʾ�������롰jps��
 * ���Կ�������ʾ����Ӧ�Ľ���ID
 * �����ټ��롰jstack ID��
 */
public class _2_ThreadState {
	public static void main(String[] args) {
		new Thread(new TimeWaiting(), "TimeWaitingThread").start();
		new Thread(new Waiting(), "WaitingThread").start();
		//ʹ������Blocked�̣߳�һ����ȡ���ɹ�����һ��������
		new Thread(new Blocked(), "BlockedThread-1").start();
		new Thread(new Blocked(), "BlockedThread-2").start();
	}
	
	//���̲߳��ϵؽ���˯��
	static class TimeWaiting implements Runnable {
		@Override
		public void run() {
			while(true) {
				_2_SleepUtils.second(100);
			}
		}
	}
	
	//���߳���Waiting.classʵ���ϵȴ�
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
	
	//���߳���Bolcked.classʵ���ϼ����󣬲����ͷŸ���
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
