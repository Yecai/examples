package com.xiaopan.thread.artbook._4_base._4_2_start_and_shutdown;

import java.util.concurrent.TimeUnit;
/**
 * @Package: com.xiaopan.thread.artbook._4_base
 * @ClassName: _4_shutdown
 * @date: 2017��8��22�� ����4:24:55
 * @Description: �����߳�
 * 1���ж�interrupt
 * 2����ʶλcancel()
 * ͨ����ʶλ�����жϲ����ķ�ʽ�ܹ�ʹ�߳�����ֹʱ�л���ȥ������Դ����������ϵ�
���߳�ֹͣ�����������ֹ�̵߳������Եø��Ӱ�ȫ������
 */
public class _4_shutdown {
	public static void main(String[] args) throws InterruptedException {
		Runner one = new Runner();
		Thread countThread = new Thread(one, "CountThread");
		countThread.start();
		//˯��1�룬main�̶߳�CountThread�����жϣ�ʹCountThread�ܹ���֪�ж϶�����
		TimeUnit.SECONDS.sleep(1);
		countThread.interrupt();
		
		Runner two = new Runner();
		countThread = new Thread(two, "CountThread");
		countThread.start();
		//˯��1�룬mian�̶߳�Runner two����ȡ����ʹCountThread�ܹ���֪onΪfalse������
		TimeUnit.SECONDS.sleep(1);
		two.cancel();
	}
	
	private static class Runner implements Runnable {
		
		private long i;
		private volatile boolean on = true;
		
		@Override
		public void run() {
			while(on && !Thread.currentThread().isInterrupted()) {
				i++;
			}
			System.out.println("�̰߳�ȫ�˳���Count i = " + i);
		}
		public void cancel() {
			on = false;
		}
	}
}
