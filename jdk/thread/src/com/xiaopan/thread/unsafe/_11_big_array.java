package com.xiaopan.thread.unsafe;

import sun.misc.Unsafe;

/**
 * @Package: com.xiaopan.thread.unsafe
 * @ClassName: _11_big_array
 * @date: 2018��3��19�� ����9:36:39
 * @Description: 
 * Java���鳤�ȵ����ֵ��Integer.MAX_VALUE��
 * ʹ��ֱ���ڴ�������ǿ��Դ����ǳ�������飬������Ĵ�Сֻ�����ڶѵĴ�С��
 */
public class _11_big_array {
    public static void main(String[] args) {
        long SUPER_SIZE = (long) Integer.MAX_VALUE * 2;
        SuperArray array = new SuperArray(SUPER_SIZE);
        System.out.println("Array size:" + array.size()); // 4294967294
        
        int sum = 0;
        for (int i = 0; i < 100; i++) {
            array.set((long) Integer.MAX_VALUE + i, (byte) 3);
            sum += array.get((long) Integer.MAX_VALUE + i);
        }
        System.out.println("Sum of 100 elements:" + sum); // 300
        /**
         * ��ʵ�ϸü���ʹ���˷Ƕ��ڴ�off-heap memory���� java.nio ����Ҳ��ʹ�á�

ͨ�����ַ�ʽ������ڴ治�ڶ��ϣ����Ҳ���GC���������ҪС��ʹ��Unsafe.freeMemory()���÷����������κα߽��飬����κβ��Ϸ��ķ��ʿ��ܾͻᵼ��JVM������

����ʹ�÷�ʽ������ѧ�����Ƿǳ����õģ���Ϊ������Բ����ǳ�����������顣 ͬ���ı�дʵʱ����ĳ���Ա�Դ�Ҳ�ǳ�����Ȥ����Ϊ����GC���ƣ��Ͳ�����ΪGC���·ǳ����ͣ�١�
         */
    }
}

class SuperArray {
    private final static int BYTE = 1;

    private long size;
    private long address;

    public SuperArray(long size) {
        this.size = size;
        address = getUnsafe().allocateMemory(size * BYTE);
    }

    public void set(long i, byte value) {
        getUnsafe().putByte(address + i * BYTE, value);
    }

    public int get(long idx) {
        return getUnsafe().getByte(address + idx * BYTE);
    }

    public long size() {
        return size;
    }
    
    public Unsafe getUnsafe() {
        return _1_GetUnsafe.getUnsafe();
    }
}
