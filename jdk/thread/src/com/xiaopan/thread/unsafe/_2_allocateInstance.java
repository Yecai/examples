package com.xiaopan.thread.unsafe;

import sun.misc.Unsafe;

/**
 * @Package: com.xiaopan.thread.unsafe
 * @ClassName: _2_UnsafeAPI
 * @date: 2018��3��19�� ����4:01:16
 * @Description: http://ifeve.com/sun-misc-unsafe/
 * sun.misc.Unsafe�����105������

Info.������һЩ�ͼ����ڴ���Ϣ
    addressSize
    pageSize
    
Objects.�ṩ���ڲ����������ֶεķ���
    allocateInstance
    objectFieldOffset
    
Classes.�ṩ���ڲ����༰�侲̬�ֶεķ���
    staticFieldOffset
    defineClass
    defineAnonymousClass
    ensureClassInitialized
    
Arrays.��������
    arrayBaseOffset
    arrayIndexScale
    
Synchronization.�ͼ���ͬ��ԭ��
    monitorEnter
    tryMonitorEnter
    monitorExit
    compareAndSwapInt
    putOrderedInt
    
Memory.ֱ���ڴ���ʷ���
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
        
        //�����ʼ��
        //������Ҫ���������ʼ���׶Σ����ƹ��������İ�ȫ��飬��ʵ����һ��û���κι������������࣬allocateInstance�����Ƿǳ����õ�
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
