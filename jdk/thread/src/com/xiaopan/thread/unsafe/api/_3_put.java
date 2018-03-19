package com.xiaopan.thread.unsafe.api;

import java.lang.reflect.Field;

import com.xiaopan.thread.unsafe._1_GetUnsafe;

import sun.misc.Unsafe;

/**
 * @Package: com.xiaopan.thread.unsafe.api
 * @ClassName: _3_put
 * @date: 2018年3月19日 下午9:14:42
 * @Description: 
 * putLong，putInt，putDouble，putChar，putObject等方法，
 * 直接修改内存数据（可以越过访问权限）
 * 还有put对应的get方法，就是直接读取内存地址处的数据
 * 
 * 以putLong(Object, long, long)为例
 * Object o //对象引用
 * long offset //对象内存地址的偏移量
 * long x //写入的数据
 * public native void    putLong(Object o, long offset, long x);
 * 
 * natUnsafe.cc中的c++实现
 * void
 * sun::misc::Unsafe::putLong (jobject obj, jlong offset, jlong value)
 * {
 *     jlong *addr = (jlong *) ((char *) obj + offset);//计算要修改的数据的内存地址=对象地址+成员属性地址偏移量
 *     spinlock lock;//自旋锁，通过循环来获取锁， i386处理器需要加锁访问64位数据，如果是int，则不需要改行代码
 *     *addr = value;//往该内存地址位置直接写入数据
 * }
 * 
 * 如下例子，即使User类的成员属性是私有的且没有提供对外的public方法，我们还是可以直接在它们的内存地址位置处写入数据，并成功；
 */
public class _3_put {
    @SuppressWarnings("rawtypes")
    public static void main(String[] args) throws Exception {
        Unsafe unsafe = _1_GetUnsafe.getUnsafe();

        User2 user = new User2();
        System.out.println(user); // 打印test,1,2,1.72

        Class userClass = user.getClass();
        Field name = userClass.getDeclaredField("name");
        Field id = userClass.getDeclaredField("id");
        Field age = userClass.getDeclaredField("age");
        Field height = userClass.getDeclaredField("height");
        // 直接往内存地址写数据
        unsafe.putObject(user, unsafe.objectFieldOffset(name), "midified-name");
        unsafe.putLong(user, unsafe.objectFieldOffset(id), 100l);
        unsafe.putInt(user, unsafe.objectFieldOffset(age), 101);
        unsafe.putDouble(user, unsafe.objectFieldOffset(height), 100.1);

        System.out.println(user);// 打印midified-name,100,101,100.1
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