package com.xiaopan.thread.artbook._4_base._4_1_Introduction;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * @Package: com.xiaopan.thread.artbook._4_base._4_1_Introduction
 * @ClassName: _4_1_MultiThread
 * @date: 2018年3月17日 上午11:46:34
 * @Description: 打印输出线程名
 */
public class _4_1_MultiThread {
    public static void main(String[] args) {
        // 获取java线程管理MXBEAN
        ThreadMXBean threadMxBean = ManagementFactory.getThreadMXBean();
        // 不需要获取同步的monitor和synchronizer信息，仅获取线程和线程堆栈信息
        ThreadInfo[] threadInfos = threadMxBean.dumpAllThreads(false, false);
        // 遍历线程信息，仅打印线程ID和线程名称信息
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println("[" + threadInfo.getThreadId() + "] " + threadInfo.getThreadName());
        }
    }
}
