package com.xiaopan.thread.artbook._8_util;

/**
 * @Package: com.xiaopan.thread.artbook._8_util
 * @ClassName: _1_JoinCountDownLatchTest
 * @date: 2018��3��20�� ����7:32:14
 * @Description: 
 * CountDownLatch����һ�������̵߳ȴ������߳���ɲ���
 * 
 * ����������һ������������Ҫ����һ��Excel����sheet�����ݣ���ʱ���Կ���ʹ�ö�
 * �̣߳�ÿ���߳̽���һ��sheet������ݣ��ȵ����е�sheet��������֮�󣬳�����Ҫ��ʾ������
 * �ɡ�����������У�Ҫʵ�����̵߳ȴ������߳����sheet�Ľ�����������򵥵�������ʹ��
 * join()����
 */
public class _1_JoinCountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        Thread parser1 = new Thread(() -> System.out.println("parser1 finish"));
        Thread parser2 = new Thread(() -> System.out.println("parser2 finish"));
        parser1.start();
        parser2.start();
        parser1.join();
        parser2.join();
        System.out.println("all parser finish");
    }
}
