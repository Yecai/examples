package com.xiaopan.thread.artbook._4_base._4_3_thread_communication;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.xiaopan.thread.artbook._4_base._4_1_Introduction._2_SleepUtils;

/**
 * @Package: com.xiaopan.thread.artbook._4_base
 * @ClassName: _5_wait_notify
 * @date: 2017��8��22�� ����5:10:06
 * @Description: �ȴ���֪ͨ
 * wait�ͷ���
 * notify����wait���̣߳����²��뾺�������߳�״̬��WAITING��ΪBLOCKED��
 * ��������������hold lock again�� �� ��notifyed����־˳����ܻ᲻һ��
 * 1��ʹ��wait()��notify()��notifyAll()ʱ��Ҫ�ȶԵ��ö��������
 * 2������wait()�������߳�״̬��RUNNING��ΪWAITING��������ǰ�̷߳��õ�����ĵȴ����С�
 * 3��notify()��notifyAll()�������ú󣬵ȴ��߳����ɲ����wait()���أ���Ҫ����notify()��notifAll()���߳��ͷ���֮�󣬵ȴ��̲߳��л����wait()���ء�
 * 4��notify()�������ȴ������е�һ���ȴ��̴߳ӵȴ��������Ƶ�ͬ�������У���notifyAll()�������ǽ��ȴ����������е��߳�ȫ���Ƶ�ͬ�����У����ƶ����߳�״̬��WAITING��ΪBLOCKED��
 * 5����wait()�������ص�ǰ���ǻ���˵��ö��������
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
			//���� ��ӵ��lock��Monitor
			synchronized (lock) {
				//������������ʱ������wait��ͬʱ�ͷ���lock����
				while(flag) {
					try {
						System.out.println(Thread.currentThread() + " flag is true. wait @ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
						lock.wait(); //wait�ͷ���lock����notify�̻߳��������ʼִ��
						//notify�����»�ȡ����lock��������ִ��
						System.out.println(Thread.currentThread() + " notifyed. running @ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
					} catch (InterruptedException e) {
						
					}
				}
				//��������ʱ����ɹ���
				System.out.println(Thread.currentThread() + " flag is false. running @ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
			}
		}
	}
	
	static class Notify implements Runnable {
		@Override
		public void run() {
			//������ӵ��lock��Monitor
			synchronized (lock) {
				//��ȡlock������Ȼ�����֪ͨ��֪ͨʱ�����ͷ�lock����
				//ֱ����ǰ�߳��ͷ���lock��WaitThread���ܴ�wait�����з���
				System.out.println(Thread.currentThread() + " hold lock. notify @ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
				lock.notifyAll();
				flag = false;
				_2_SleepUtils.second(2);
			}
			//Notify�ͷ�����
//			_2_SleepUtils.second(2);
			
			//�ٴμ���(��ʱ��Notify��Waitͬʱ��������
			synchronized (lock) {
				System.out.println(Thread.currentThread() + " hold lock again. sleep @ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
				_2_SleepUtils.second(5);
			}
		}
	}
}
