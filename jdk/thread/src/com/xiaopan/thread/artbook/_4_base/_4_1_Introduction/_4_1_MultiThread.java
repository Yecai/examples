package com.xiaopan.thread.artbook._4_base._4_1_Introduction;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * @Package: com.xiaopan.thread.artbook._4_base._4_1_Introduction
 * @ClassName: _4_1_MultiThread
 * @date: 2018��3��17�� ����11:46:34
 * @Description: ��ӡ����߳���
 */
public class _4_1_MultiThread {
    public static void main(String[] args) {
        // ��ȡjava�̹߳���MXBEAN
        ThreadMXBean threadMxBean = ManagementFactory.getThreadMXBean();
        // ����Ҫ��ȡͬ����monitor��synchronizer��Ϣ������ȡ�̺߳��̶߳�ջ��Ϣ
        ThreadInfo[] threadInfos = threadMxBean.dumpAllThreads(false, false);
        // �����߳���Ϣ������ӡ�߳�ID���߳�������Ϣ
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println("[" + threadInfo.getThreadId() + "] " + threadInfo.getThreadName());
        }
    }
}
