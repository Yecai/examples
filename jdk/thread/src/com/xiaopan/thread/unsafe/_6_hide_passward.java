package com.xiaopan.thread.unsafe;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

/**
 * @Package: com.xiaopan.thread.unsafe
 * @ClassName: _6_hide_passward
 * @date: 2018年3月19日 下午8:57:03
 * @Description: 隐藏密码（Hide Password）
在Unsafe中，一个更有趣的直接内存访问的用法是，从内存中删除不必要的对象。
检索用户密码的大多数API的签名为byte[]或char[]，为什么是数组呢？
这完全是出于安全的考虑，因为我们可以删除不需要的数组元素。如果将用户密码检索成字符串，这可以像一个对象一样在内存中保存，而删除该对象只需执行解除引用的操作。但是，这个对象仍然在内存中，由GC决定的时间来执行清除。

创建具有相同大小、假的String对象，来取代在内存中原来的String对象的技巧：
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
         * 感觉很安全。
         * 修改：这并不安全。为了真正的安全，我们需要通过反射删除后台char数组：
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
