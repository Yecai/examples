package com.xiaopan.thread.unsafe;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

/**
 * @Package: com.xiaopan.thread.unsafe
 * @ClassName: _1_GetUnsafe
 * @date: 2018年3月19日 下午3:48:17
 * @Description: https://leokongwq.github.io/2016/12/31/java-magic-unsafe.html
 * 作用：
 *     Unsafe可以用来在任意内存地址位置处读写数据，同时还支持一些CAS原子操作
 * 获取Unsafe对象：
 *     Unsafe对象不能直接通过new Unsafe()获取，因为Unsafe被设计成单例模式，构造方法是私有的
 *     Unsafe对象不能通过调用Unsafe.getUnsafe()获取，因为getUnsafe被设计成只能从引导类加载器（bootstrap class loader）加载，代码如下
 *     
 *          @CallerSensitive
 *          public static Unsafe getUnsafe() {
 *              //得到调用该方法的Class对象
 *              Class cc = Reflection.getCallerClass();
 *              //判断调用该方法的类是否是引导类加载器（bootstrap class loader）
 *              //如果不是的话，比如由AppClassLoader调用该方法，则抛出SecurityException异常
 *              if (cc.getClassLoader() != null)
 *                  throw new SecurityException("Unsafe");
 *              //返回单例对象
 *              return theUnsafe;
 *          }
 *
 *
 */
public class _1_GetUnsafe {
    
    public static void main(String[] args) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        // 获取方式一：运行程序时，使用bootclasspath 选项，指定系统类路径加上你使用的一个Unsafe路径。
        // java -Xbootclasspath:/usr/jdk1.7.0/jre/lib/rt.jar:.
        // com.xiaopan.thread.unsafe._1_GetUnsafe

        // 获取方式二：Unsafe类中有个私有的静态全局属性theUnsafe（Unsafe实例对象），通过反射，可以获取到该成员属性theUnsafe对应的Field对象，并将其设置为可访问，从而得到theUnsafe具体对象
        // 通过反射得到theUnsafe对应的Field对象
        Field field = Unsafe.class.getDeclaredField("theUnsafe");
        // 设置该Field为可访问
        field.setAccessible(true);
        // 通过Field得到该Field对应的具体对象，传入null是因为该Field为static的
        Unsafe unsafe = (Unsafe) field.get(null);
        System.out.println(unsafe);
    }
    

    private static Unsafe unsafe;
    public static Unsafe getUnsafe() {
        if (null != unsafe) {
            return unsafe;
        }
        try {
            // 通过反射得到theUnsafe对应的Field对象
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            // 设置该Field为可访问
            field.setAccessible(true);
            // 通过Field得到该Field对应的具体对象，传入null是因为该Field为static的
            unsafe = (Unsafe) field.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return unsafe;
    }
}
