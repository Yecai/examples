package com.xiaopan.thread.unsafe;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

/**
 * @Package: com.xiaopan.thread.unsafe
 * @ClassName: _6_hide_passward
 * @date: 2018��3��19�� ����8:57:03
 * @Description: �������루Hide Password��
��Unsafe�У�һ������Ȥ��ֱ���ڴ���ʵ��÷��ǣ����ڴ���ɾ������Ҫ�Ķ���
�����û�����Ĵ����API��ǩ��Ϊbyte[]��char[]��Ϊʲô�������أ�
����ȫ�ǳ��ڰ�ȫ�Ŀ��ǣ���Ϊ���ǿ���ɾ������Ҫ������Ԫ�ء�������û�����������ַ������������һ������һ�����ڴ��б��棬��ɾ���ö���ֻ��ִ�н�����õĲ��������ǣ����������Ȼ���ڴ��У���GC������ʱ����ִ�������

����������ͬ��С���ٵ�String������ȡ�����ڴ���ԭ����String����ļ��ɣ�
 */
public class _6_hide_passward {
    public static void main(String[] args) throws Exception {
        String password = new String("l00k@myHor$e");
        String fake = new String(password.replaceAll(".", "?"));
        System.out.println(password); // l00k@myHor$e
        System.out.println(fake); // ????????????

        getUnsafe().copyMemory(fake, 0L, null, _5_shallowCopy.toAddress(password), _4_sizeOf.sizeOf(password));

        System.out.println(password); // ????????????
        System.out.println(fake); // ????????????
        
        /**
         * �о��ܰ�ȫ��
         * �޸ģ��Ⲣ����ȫ��Ϊ�������İ�ȫ��������Ҫͨ������ɾ����̨char���飺
         */
        Field stringValue = String.class.getDeclaredField("value");
        stringValue.setAccessible(true);
        char[] mem = (char[]) stringValue.get(password);
        for (int i=0; i < mem.length; i++) {
          mem[i] = '?';
        }
    }
    public static Unsafe getUnsafe() {
        return _1_GetUnsafe.getUnsafe();
    }
}
