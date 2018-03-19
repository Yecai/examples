package com.xiaopan.thread.unsafe;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashSet;

import sun.misc.Unsafe;

/**
 * @Package: com.xiaopan.thread.unsafe
 * @ClassName: _4_sizeOf
 * @date: 2018��3��19�� ����7:27:21
 * @Description: 
 * ʹ��objectFieldOffset��������ʵ��C-���C-style����sizeof������
 * ���ʵ�ַ��ض���������ڴ��С������ע��shallow size����
 */
public class _4_sizeOf {
    public static void main(String[] args) {
        
    }
    
    /**
     * ͨ�����зǾ�̬�ֶΣ���������ģ�����ȡÿ���ֶε�ƫ������offset����
     * �ҵ�ƫ�����ֵ������ֽ���(padding)��
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
    
    //������ǽ���ȡ�������ṹ��Сֵ��sizeOf��ʵ�ֿ��Ը��򵥣���λ��JVM 1.7 32 bit�е�ƫ����12��
//    public static long sizeOf(Object object) {
//        return getUnsafe().getAddress(normalize(getUnsafe().getInt(object, 4L)) + 12L);
//    }
    /**
     * normalize��һ��Ϊ����ȷ�ڴ��ַʹ�ã����з��ŵ�int����ǿ��ת�����޷��ŵ�long���͵ķ���
     */
    public static long normalize(int value) {
        if (value >= 0) return value;
        return (~0L >>> 32) & value;
    }
    
    public static Unsafe getUnsafe() {
        return _1_GetUnsafe.getUnsafe();
    }
}
