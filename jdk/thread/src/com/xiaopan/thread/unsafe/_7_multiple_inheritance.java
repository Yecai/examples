package com.xiaopan.thread.unsafe;

import sun.misc.Unsafe;

/**
 * @Package: com.xiaopan.thread.unsafe
 * @ClassName: _7_multiple_inheritance
 * @date: 2018年3月19日 下午9:00:41
 * @Description: Java中没有多继承。
这是对的，除非我们可以将任意类型转换成我们想要的其他类型。
 */
public class _7_multiple_inheritance {
    public static void main(String[] args) {
        long intClassAddress = _4_sizeOf.normalize(getUnsafe().getInt(new Integer(0), 4L));
        long strClassAddress = _4_sizeOf.normalize(getUnsafe().getInt("", 4L));
        getUnsafe().putAddress(intClassAddress + 36, strClassAddress);
        //这个代码片段将String类型添加到Integer超类中，因此我们可以强制转换，且没有运行时异常。
        
//        (String) (Object) (new Integer(666));
        //有一个问题，我们必须预先强制转换对象，以欺骗编译器。
    }
    public static Unsafe getUnsafe() {
        return _1_GetUnsafe.getUnsafe();
    }
}
