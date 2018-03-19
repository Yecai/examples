package com.xiaopan.thread.unsafe;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

/**
 * @Package: com.xiaopan.thread.unsafe
 * @ClassName: _3_memory_corruption
 * @date: 2018��3��19�� ����6:30:46
 * @Description: �ڴ������Memory corruption��
 * �ƹ���ȫ�ĳ��ü���
 */
public class _3_memory_corruption {
    public static void main(String[] args) throws Exception {
        /**
         * �ͻ��˴����Ƿǳ���ȫ�ģ�����ͨ������giveAccess()�������ʹ���
         * ��ϧ�����ڿͻ��������Ƿ���false��ֻ����Ȩ�û�������ĳ�ַ�ʽ�ı�
         * ACCESS_ALLOWED������ֵ���ҵõ����ʣ�giveAccess()��������true������ע��
         * ʵ���ϣ��Ⲣ������ġ���ʾ�������£�
         */
        
        Guard guard = new Guard();
        System.out.println(guard.giveAccess());
        
        //bypass
        Unsafe unsafe = _1_GetUnsafe.getUnsafe();
        Field f = guard.getClass().getDeclaredField("ACCESS_ALLOWED");
        unsafe.putInt(guard, unsafe.objectFieldOffset(f), 42);
        System.out.println(guard.giveAccess());
        
        /**
         * �������еĿͻ���ӵ�������Ƶķ���Ȩ�ޡ�
         * ʵ���ϣ��������ʵ����ͬ�Ĺ��ܡ���ֵ�ù�ע���ǣ����ǿ����޸��κζ�������û����Щ��������á�
         * ���磬��һ��guard���������ڴ��е�λ�ý������ڵ�ǰguard����֮�����ǿ��������´������޸�����
         */
//        unsafe.putInt(guard, 16 + unsafe.objectFieldOffset(f), 42); // memory corruption
        /**
         * ע�⣺���ǲ��س��������������á�16��Guard������32λ�ܹ��ϵĴ�С�����ǿ����ֹ�������������ͨ��ʹ��sizeOf���������Ķ��壬���½ڣ���
         */
    }
}

/**
 * ���ڼ�顰���ʹ��򡱵ļ���
 */
class Guard {
    private int ACCESS_ALLOWED = 1;

    public boolean giveAccess() {
           return 42 == ACCESS_ALLOWED;
    }
}
