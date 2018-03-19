package com.xiaopan.thread.unsafe;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

/**
 * @Package: com.xiaopan.thread.unsafe
 * @ClassName: _3_memory_corruption
 * @date: 2018年3月19日 下午6:30:46
 * @Description: 内存崩溃（Memory corruption）
 * 绕过安全的常用技术
 */
public class _3_memory_corruption {
    public static void main(String[] args) throws Exception {
        /**
         * 客户端代码是非常安全的，并且通过调用giveAccess()来检查访问规则。
         * 可惜，对于客户，它总是返回false。只有特权用户可以以某种方式改变
         * ACCESS_ALLOWED常量的值并且得到访问（giveAccess()方法返回true，译者注）
         * 实际上，这并不是真的。演示代码如下：
         */
        
        Guard guard = new Guard();
        System.out.println(guard.giveAccess());
        
        //bypass
        Unsafe unsafe = _1_GetUnsafe.getUnsafe();
        Field f = guard.getClass().getDeclaredField("ACCESS_ALLOWED");
        unsafe.putInt(guard, unsafe.objectFieldOffset(f), 42);
        System.out.println(guard.giveAccess());
        
        /**
         * 现在所有的客户都拥有无限制的访问权限。
         * 实际上，反射可以实现相同的功能。但值得关注的是，我们可以修改任何对象，甚至没有这些对象的引用。
         * 例如，有一个guard对象，所在内存中的位置紧接着在当前guard对象之后。我们可以用以下代码来修改它的
         */
//        unsafe.putInt(guard, 16 + unsafe.objectFieldOffset(f), 42); // memory corruption
        /**
         * 注意：我们不必持有这个对象的引用。16是Guard对象在32位架构上的大小。我们可以手工计算它，或者通过使用sizeOf方法（它的定义，如下节）。
         */
    }
}

/**
 * 用于检查“访问规则”的简单类
 */
class Guard {
    private int ACCESS_ALLOWED = 1;

    public boolean giveAccess() {
           return 42 == ACCESS_ALLOWED;
    }
}
