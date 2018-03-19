package com.xiaopan.thread.unsafe.api;

/**
 * @Package: com.xiaopan.thread.unsafe.api
 * @ClassName: _5_cas
 * @date: 2018年3月19日 下午9:26:13
 * @Description: 
 * CAS操作的方法，compareAndSwapInt，compareAndSwapLong等
 * natUnsafe.cc中的c++实现:
 * 将内存值与预期值作比较，判断是否相等，相等的话，写入数据，不相等不做操作，返回旧数据
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
 * J.U.C里原子类就是基于以上CAS操作实现的
 */
public class _5_cas {

}
