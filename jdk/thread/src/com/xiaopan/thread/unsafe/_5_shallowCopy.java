package com.xiaopan.thread.unsafe;

import sun.misc.Unsafe;

public class _5_shallowCopy {
    public static void main(String[] args) {
        
    }
    
    public static Object shallowCopy(Object obj) throws Exception {
        long size = _4_sizeOf.sizeOf(obj);
        long start = toAddress(obj);
        Unsafe unsafe = getUnsafe();
        long address = unsafe.allocateMemory(size);
        unsafe.copyMemory(start, address, size);
        return fromAddress(address);
    }

    /**
     * toAddress��fromAddress������ת��Ϊ�����ڴ��еĵ�ַ����֮��Ȼ��
     */
    public static long toAddress(Object obj) {
        Object[] array = new Object[] {obj};
        long baseOffset = getUnsafe().arrayBaseOffset(Object[].class);
        return _4_sizeOf.normalize(getUnsafe().getInt(array, baseOffset));
    }
    
    public static Object fromAddress(long address) {
        Object[] array = new Object[] {null};
        long baseOffset = getUnsafe().arrayBaseOffset(Object[].class);
        getUnsafe().putLong(array, baseOffset, address);
        return array[0];
    }
    /**
     * ������������������������κ����͵Ķ��󣬶�̬�������Ĵ�С��ע�⣬�ڿ���������Ҫ������ת�����ض������͡�
     */
    
    public static Unsafe getUnsafe() {
        return _1_GetUnsafe.getUnsafe();
    }
}
