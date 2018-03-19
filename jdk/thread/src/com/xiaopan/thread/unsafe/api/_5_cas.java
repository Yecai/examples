package com.xiaopan.thread.unsafe.api;

/**
 * @Package: com.xiaopan.thread.unsafe.api
 * @ClassName: _5_cas
 * @date: 2018��3��19�� ����9:26:13
 * @Description: 
 * CAS�����ķ�����compareAndSwapInt��compareAndSwapLong��
 * natUnsafe.cc�е�c++ʵ��:
 * ���ڴ�ֵ��Ԥ��ֵ���Ƚϣ��ж��Ƿ���ȣ���ȵĻ���д�����ݣ�����Ȳ������������ؾ�����
 * static inline bool
 * compareAndSwap (volatile jint *addr, jint old, jint new_val)
 * {
 *   jboolean result = false;
 *   spinlock lock;
 *   if ((result = (*addr == old)))
 *     *addr = new_val;
 *   return result;
 * }
 * 
 * J.U.C��ԭ������ǻ�������CAS����ʵ�ֵ�
 */
public class _5_cas {

}
