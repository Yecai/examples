package com.xiaopan.thread.artbook._8_util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import com.xiaopan.thread.artbook._4_base._4_1_Introduction.SleepUtils;

/**
 * @Package: com.xiaopan.thread.artbook._8_util
 * @ClassName: _6_SemaphoreTest
 * @date: 2018��3��21�� ����2:24:30
 * @Description:
 * Semaphore���ź���������������ͬʱ�����ض���Դ���߳���������ͨ��Э�������̣߳���
 * ��֤�����ʹ�ù�����Դ��
 * Semaphore�����������������ƣ��ر��ǹ�����Դ���޵�Ӧ�ó���
 * 
 * �ڴ����У���Ȼ��30���߳���ִ�У�����ֻ����10������ִ�С�Semaphore�Ĺ��췽��
 * Semaphore��int permits������һ�����͵����֣���ʾ���õ����֤������Semaphore��10����ʾ��
 * ��10���̻߳�ȡ���֤��Ҳ������󲢷�����10��Semaphore���÷�Ҳ�ܼ򵥣������߳�ʹ��
 * Semaphore��acquire()������ȡһ�����֤��ʹ����֮�����release()�����黹���֤��������
 * ��tryAcquire()�������Ի�ȡ���֤��
 */
public class _6_SemaphoreTest {
    private static final int THREAD_COUNT = 30;
    private static ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_COUNT);
    
    private static Semaphore s = new Semaphore(10);
    public static void main(String[] args) {
        for (int i = 0; i < THREAD_COUNT; i++) {
            threadPool.execute(() -> {
                try {
                    s.acquire();
                    SleepUtils.second(2);
                    System.out.println(Thread.currentThread().getName() + ":save data");
                    s.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        threadPool.shutdown();
    }
}
