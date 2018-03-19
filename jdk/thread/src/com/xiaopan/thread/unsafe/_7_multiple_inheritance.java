package com.xiaopan.thread.unsafe;

import sun.misc.Unsafe;

/**
 * @Package: com.xiaopan.thread.unsafe
 * @ClassName: _7_multiple_inheritance
 * @date: 2018��3��19�� ����9:00:41
 * @Description: Java��û�ж�̳С�
���ǶԵģ��������ǿ��Խ���������ת����������Ҫ���������͡�
 */
public class _7_multiple_inheritance {
    public static void main(String[] args) {
        long intClassAddress = _4_sizeOf.normalize(getUnsafe().getInt(new Integer(0), 4L));
        long strClassAddress = _4_sizeOf.normalize(getUnsafe().getInt("", 4L));
        getUnsafe().putAddress(intClassAddress + 36, strClassAddress);
        //�������Ƭ�ν�String������ӵ�Integer�����У�������ǿ���ǿ��ת������û������ʱ�쳣��
        
//        (String) (Object) (new Integer(666));
        //��һ�����⣬���Ǳ���Ԥ��ǿ��ת����������ƭ��������
    }
    public static Unsafe getUnsafe() {
        return _1_GetUnsafe.getUnsafe();
    }
}
