package com.xiaopan.thread.unsafe;

import java.io.IOException;

import sun.misc.Unsafe;

/**
 * @Package: com.xiaopan.thread.unsafe
 * @ClassName: _9_exception
 * @date: 2018年3月19日 下午9:32:31
 * @Description: 
 * 
 */
public class _9_exception {
    public static void main(String[] args) {
        //不喜欢受检异常？这不是问题。
        getUnsafe().throwException(new IOException());
        //该方法抛出一个受检异常，但是你的代码不需要强制捕获该异常就像运行时异常一样。
    }
    public static Unsafe getUnsafe() {
        return _1_GetUnsafe.getUnsafe();
    }
}
