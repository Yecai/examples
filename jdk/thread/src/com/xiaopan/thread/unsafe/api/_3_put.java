package com.xiaopan.thread.unsafe.api;

import java.lang.reflect.Field;

import com.xiaopan.thread.unsafe._1_GetUnsafe;

import sun.misc.Unsafe;

/**
 * @Package: com.xiaopan.thread.unsafe.api
 * @ClassName: _3_put
 * @date: 2018��3��19�� ����9:14:42
 * @Description: 
 * putLong��putInt��putDouble��putChar��putObject�ȷ�����
 * ֱ���޸��ڴ����ݣ�����Խ������Ȩ�ޣ�
 * ����put��Ӧ��get����������ֱ�Ӷ�ȡ�ڴ��ַ��������
 * 
 * ��putLong(Object, long, long)Ϊ��
 * Object o //��������
 * long offset //�����ڴ��ַ��ƫ����
 * long x //д�������
 * public native void    putLong(Object o, long offset, long x);
 * 
 * natUnsafe.cc�е�c++ʵ��
 * void
 * sun::misc::Unsafe::putLong (jobject obj, jlong offset, jlong value)
 * {
 *     jlong *addr = (jlong *) ((char *) obj + offset);//����Ҫ�޸ĵ����ݵ��ڴ��ַ=�����ַ+��Ա���Ե�ַƫ����
 *     spinlock lock;//��������ͨ��ѭ������ȡ���� i386��������Ҫ��������64λ���ݣ������int������Ҫ���д���
 *     *addr = value;//�����ڴ��ַλ��ֱ��д������
 * }
 * 
 * �������ӣ���ʹUser��ĳ�Ա������˽�е���û���ṩ�����public���������ǻ��ǿ���ֱ�������ǵ��ڴ��ַλ�ô�д�����ݣ����ɹ���
 */
public class _3_put {
    @SuppressWarnings("rawtypes")
    public static void main(String[] args) throws Exception {
        Unsafe unsafe = _1_GetUnsafe.getUnsafe();

        User2 user = new User2();
        System.out.println(user); // ��ӡtest,1,2,1.72

        Class userClass = user.getClass();
        Field name = userClass.getDeclaredField("name");
        Field id = userClass.getDeclaredField("id");
        Field age = userClass.getDeclaredField("age");
        Field height = userClass.getDeclaredField("height");
        // ֱ�����ڴ��ַд����
        unsafe.putObject(user, unsafe.objectFieldOffset(name), "midified-name");
        unsafe.putLong(user, unsafe.objectFieldOffset(id), 100l);
        unsafe.putInt(user, unsafe.objectFieldOffset(age), 101);
        unsafe.putDouble(user, unsafe.objectFieldOffset(height), 100.1);

        System.out.println(user);// ��ӡmidified-name,100,101,100.1
    }
}

class User2 {
    private String name = "test"; 
    private long id = 1;
    private int age = 2;
    private double height = 1.72;
    

    @Override
    public String toString() {
        return name + "," + id + "," + age + "," + height;
    }
}