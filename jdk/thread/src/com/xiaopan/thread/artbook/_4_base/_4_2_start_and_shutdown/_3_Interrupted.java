package com.xiaopan.thread.artbook._4_base._4_2_start_and_shutdown;

import java.util.concurrent.TimeUnit;

import com.xiaopan.thread.artbook._4_base._4_1_Introduction._2_SleepUtils;

/**
 * @Package: com.xiaopan.thread.artbook._4_base._4_2_start_and_shutdown
 * @ClassName: _3_Interrupted
 * �ж�ֻ�Ǹ��̷߳����˸���Ϣ���߳���Ҫ�Լ�����ж�״̬isInterrupted()������Ӧ������sleep�ȷ����׳����ж��쳣��catch�����н�����Ӧ
 * �������д��Ӧ���룬�߳̽������ж�
 * try catch InterruptedException ������ж�״̬
 * һֱbusyæµ���߳�û������ж�״̬
 */
public class _3_Interrupted {
	public static void main(String[] args) throws InterruptedException {
		//sleepThread��ͣ�ĳ���˯��
		Thread sleepThread = new Thread(new SleepRunner(), "SleepThread");
		sleepThread.setDaemon(true);
		//busyThread��ͣ������
		Thread busyThread = new Thread(new BusyRunner(), "BusyThread");
		busyThread.setDaemon(true);
		
		sleepThread.start();
		busyThread.start();
		
		//����5�룬��sleepThread��busyThread�������
		TimeUnit.SECONDS.sleep(5);
		
		sleepThread.interrupt();
		busyThread.interrupt();
		
		System.out.println("SleepThread interrupted is " + sleepThread.isInterrupted());
		System.out.println("busyThread interrupted is " + busyThread.isInterrupted());
		
		//��ֹsleepThread��busyThread�����˳�
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
