package com.xiaopan.thread.unsafe;

import java.io.IOException;

import sun.misc.Unsafe;

/**
 * @Package: com.xiaopan.thread.unsafe
 * @ClassName: _9_exception
 * @date: 2018��3��19�� ����9:32:31
 * @Description: 
 * 
 */
public class _9_exception {
    public static void main(String[] args) {
        //��ϲ���ܼ��쳣���ⲻ�����⡣
        getUnsafe().throwException(new IOException());
        //�÷����׳�һ���ܼ��쳣��������Ĵ��벻��Ҫǿ�Ʋ�����쳣��������ʱ�쳣һ����
    }
    public static Unsafe getUnsafe() {
        return _1_GetUnsafe.getUnsafe();
    }
}
