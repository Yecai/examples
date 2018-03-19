package com.xiaopan.thread.unsafe;

import sun.misc.Unsafe;

/**
 * @Package: com.xiaopan.thread.unsafe
 * @ClassName: _11_big_array
 * @date: 2018年3月19日 下午9:36:39
 * @Description: 
 * Java数组长度的最大值是Integer.MAX_VALUE。
 * 使用直接内存分配我们可以创建非常大的数组，该数组的大小只受限于堆的大小。
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
         * 事实上该技术使用了非堆内存off-heap memory，在 java.nio 包中也有使用。

通过这种方式分配的内存不在堆上，并且不受GC管理。因此需要小心使用Unsafe.freeMemory()。该方法不会做任何边界检查，因此任何不合法的访问可能就会导致JVM奔溃。

这种使用方式对于数学计算是非常有用的，因为代码可以操作非常大的数据数组。 同样的编写实时程序的程序员对此也非常感兴趣，因为不受GC限制，就不会因为GC导致非常大的停顿。
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
