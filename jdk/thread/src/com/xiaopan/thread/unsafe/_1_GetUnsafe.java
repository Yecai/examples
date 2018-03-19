package com.xiaopan.thread.unsafe;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

/**
 * @Package: com.xiaopan.thread.unsafe
 * @ClassName: _1_GetUnsafe
 * @date: 2018��3��19�� ����3:48:17
 * @Description: https://leokongwq.github.io/2016/12/31/java-magic-unsafe.html
 * ���ã�
 *     Unsafe���������������ڴ��ַλ�ô���д���ݣ�ͬʱ��֧��һЩCASԭ�Ӳ���
 * ��ȡUnsafe����
 *     Unsafe������ֱ��ͨ��new Unsafe()��ȡ����ΪUnsafe����Ƴɵ���ģʽ�����췽����˽�е�
 *     Unsafe������ͨ������Unsafe.getUnsafe()��ȡ����ΪgetUnsafe����Ƴ�ֻ�ܴ��������������bootstrap class loader�����أ���������
 *     
 *          @CallerSensitive
 *          public static Unsafe getUnsafe() {
 *              //�õ����ø÷�����Class����
 *              Class cc = Reflection.getCallerClass();
 *              //�жϵ��ø÷��������Ƿ����������������bootstrap class loader��
 *              //������ǵĻ���������AppClassLoader���ø÷��������׳�SecurityException�쳣
 *              if (cc.getClassLoader() != null)
 *                  throw new SecurityException("Unsafe");
 *              //���ص�������
 *              return theUnsafe;
 *          }
 *
 *
 */
public class _1_GetUnsafe {
    
    public static void main(String[] args) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        // ��ȡ��ʽһ�����г���ʱ��ʹ��bootclasspath ѡ�ָ��ϵͳ��·��������ʹ�õ�һ��Unsafe·����
        // java -Xbootclasspath:/usr/jdk1.7.0/jre/lib/rt.jar:.
        // com.xiaopan.thread.unsafe._1_GetUnsafe

        // ��ȡ��ʽ����Unsafe�����и�˽�еľ�̬ȫ������theUnsafe��Unsafeʵ�����󣩣�ͨ�����䣬���Ի�ȡ���ó�Ա����theUnsafe��Ӧ��Field���󣬲���������Ϊ�ɷ��ʣ��Ӷ��õ�theUnsafe�������
        // ͨ������õ�theUnsafe��Ӧ��Field����
        Field field = Unsafe.class.getDeclaredField("theUnsafe");
        // ���ø�FieldΪ�ɷ���
        field.setAccessible(true);
        // ͨ��Field�õ���Field��Ӧ�ľ�����󣬴���null����Ϊ��FieldΪstatic��
        Unsafe unsafe = (Unsafe) field.get(null);
        System.out.println(unsafe);
    }
    

    private static Unsafe unsafe;
    public static Unsafe getUnsafe() {
        if (null != unsafe) {
            return unsafe;
        }
        try {
            // ͨ������õ�theUnsafe��Ӧ��Field����
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            // ���ø�FieldΪ�ɷ���
            field.setAccessible(true);
            // ͨ��Field�õ���Field��Ӧ�ľ�����󣬴���null����Ϊ��FieldΪstatic��
            unsafe = (Unsafe) field.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return unsafe;
    }
}
