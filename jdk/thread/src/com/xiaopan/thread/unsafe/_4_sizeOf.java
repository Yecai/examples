package com.xiaopan.thread.unsafe;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashSet;

import sun.misc.Unsafe;

/**
 * @Package: com.xiaopan.thread.unsafe
 * @ClassName: _4_sizeOf
 * @date: 2018年3月19日 下午7:27:21
 * @Description: 
 * 使用objectFieldOffset方法可以实现C-风格（C-style）的sizeof方法。
 * 这个实现返回对象的自身内存大小（译者注：shallow size）。
 */
public class _4_sizeOf {
    public static void main(String[] args) {
        
    }
    
    /**
     * 通过所有非静态字段（包含父类的），获取每个字段的偏移量（offset），
     * 找到偏移最大值并填充字节数(padding)。
     */
    @SuppressWarnings("rawtypes")
    public static long sizeOf(Object o) throws Exception {
        Unsafe u  = getUnsafe();
        HashSet<Field> fields = new HashSet<>();
        Class c = o.getClass();
        while (c != Object.class) {
            for (Field f : c.getDeclaredFields()) {
                if ((f.getModifiers() & Modifier.STATIC) == 0) {
                    fields.add(f);
                }
            }
            c = c.getSuperclass();
        }
        
        //get offset
        long maxSize = 0;
        for (Field f : fields) {
            long offset = u.objectFieldOffset(f);
            if (offset > maxSize) {
                maxSize = offset;
            }
        }
        return ((maxSize/8) + 1) * 8;  // padding
    }
    
    //如果我们仅读取对象的类结构大小值，sizeOf的实现可以更简单，这位于JVM 1.7 32 bit中的偏移量12。
//    public static long sizeOf(Object object) {
//        return getUnsafe().getAddress(normalize(getUnsafe().getInt(object, 4L)) + 12L);
//    }
    /**
     * normalize是一个为了正确内存地址使用，将有符号的int类型强制转换成无符号的long类型的方法
     */
    public static long normalize(int value) {
        if (value >= 0) return value;
        return (~0L >>> 32) & value;
    }
    
    public static Unsafe getUnsafe() {
        return _1_GetUnsafe.getUnsafe();
    }
}
