package com.xiaopan.bio;

import java.util.Random;

/**
 * @Description: ���Գ���
 */
public class Test {
	public static void main(String[] args) throws InterruptedException {
		//���з�����
//		new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				try {
//					ServerNormal.start();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}).start();
//		//����ͻ������ڷ���������ǰִ�д���
//		Thread.sleep(2000);
		//���пͻ���
		char operators[] = {'+','-','*','/'};
		Random random = new Random(System.currentTimeMillis());
		new Thread(new Runnable() {
			
			@SuppressWarnings("static-access")
			@Override
			public void run() {
//				while(true) {
					//��������������ʽ
					String expression = random.nextInt(10) + "" + operators[random.nextInt(4)] + (random.nextInt(10) + 1);
					Client.send(expression);
					try {
						Thread.currentThread().sleep(random.nextInt(1000));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
//				}
			}
		}).start();
		
	}
}
