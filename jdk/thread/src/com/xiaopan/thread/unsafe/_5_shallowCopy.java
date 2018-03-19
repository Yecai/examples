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
     * toAddress和fromAddress将对象转换为其在内存中的地址，反之亦然。
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
     * 这个拷贝方法可以用来拷贝任何类型的对象，动态计算它的大小。注意，在拷贝后，你需要将对象转换成特定的类型。
     */
    
    public static Unsafe getUnsafe() {
        return _1_GetUnsafe.getUnsafe();
    }
}
