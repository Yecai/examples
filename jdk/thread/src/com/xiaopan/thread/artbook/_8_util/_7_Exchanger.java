package com.xiaopan.thread.artbook._8_util;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Package: com.xiaopan.thread.artbook._8_util
 * @ClassName: _7_Exchanger
 * @date: 2018��3��21�� ����2:32:19
 * @Description: 
 * Exchanger�������ߣ���һ�������̼߳�Э���Ĺ����ࡣExchanger���ڽ����̼߳�����ݽ�
�������ṩһ��ͬ���㣬�����ͬ���㣬�����߳̿��Խ����˴˵����ݡ��������߳�ͨ��
exchange�����������ݣ������һ���߳���ִ��exchange()����������һֱ�ȴ��ڶ����߳�Ҳ
ִ��exchange�������������̶߳�����ͬ����ʱ���������߳̾Ϳ��Խ������ݣ������߳�����
���������ݴ��ݸ��Է���
 */
public class _7_Exchanger {
    private static final Exchanger<String> exgr = new Exchanger<>();
    private static ExecutorService threadPool = Executors.newFixedThreadPool(2);
    
    public static void main(String[] args) {
        threadPool.execute(() -> {
            try {
                String A = "������ˮA"; // A¼��������ˮ����
                exgr.exchange(A);
            } catch (InterruptedException e) {
            }
        });
        
        threadPool.execute(() -> {
            try {
                String B = "������ˮB"; // A¼��������ˮ����
                String A = exgr.exchange("B");
                System.out.println("A��B�����Ƿ�һ�£�" + A.equals(B) + ",A¼��������ǣ�" + A + ",B¼��������� ��" + B);
            } catch (InterruptedException e) {
            }
        });
        
        threadPool.shutdown();
        /**
         * ��������߳���һ��û��ִ��exchange()���������һֱ�ȴ���������������������
         * ��������һֱ�ȴ�������ʹ��exchange��V x��longtimeout��TimeUnit unit���������ȴ�ʱ����
         */
    }
}
