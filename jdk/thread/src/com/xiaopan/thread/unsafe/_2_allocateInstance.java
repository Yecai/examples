package com.xiaopan.thread.unsafe;

import sun.misc.Unsafe;

/**
 * @Package: com.xiaopan.thread.unsafe
 * @ClassName: _2_UnsafeAPI
 * @date: 2018年3月19日 下午4:01:16
 * @Description: http://ifeve.com/sun-misc-unsafe/
 * sun.misc.Unsafe类包含105个方法

Info.仅返回一些低级的内存信息
    addressSize
    pageSize
    
Objects.提供用于操作对象及其字段的方法
    allocateInstance
    objectFieldOffset
    
Classes.提供用于操作类及其静态字段的方法
    staticFieldOffset
    defineClass
    defineAnonymousClass
    ensureClassInitialized
    
Arrays.操作数组
    arrayBaseOffset
    arrayIndexScale
    
Synchronization.低级的同步原语
    monitorEnter
    tryMonitorEnter
    monitorExit
    compareAndSwapInt
    putOrderedInt
    
Memory.直接内存访问方法
    allocateMemory
    copyMemory
    freeMemory
    getAddress
    getInt
    putInt
 */
public class _2_allocateInstance {
    public static void main(String[] args) throws Exception {
        Unsafe unsafe = _1_GetUnsafe.getUnsafe();
        
        //避免初始化
        //当你想要跳过对象初始化阶段，或绕过构造器的安全检查，或实例化一个没有任何公共构造器的类，allocateInstance方法是非常有用的
        A o1 = new A(); // Constructor
        System.out.println(o1.a());
        A o2 = A.class.newInstance(); // reflection
        System.out.println(o2.a());
        A o3 = (A) unsafe.allocateInstance(A.class);
        System.out.println(o3.a());
    
    }
}

class A {
    private long a; // not initialized value
    public A() {
        this.a = 1; // initialization
    }
    public long a() {
        return this.a;
    }
}
