package com.xiaopan.thread.unsafe.api;

/**
 * @Package: com.xiaopan.thread.unsafe.api
 * @ClassName: _2_objectFieldOffset
 * @date: 2018年3月19日 下午9:13:26
 * @Description:
 * objectFieldOffset方法，返回成员属性在内存中的地址相对于对象内存地址的偏移量
 * 
 * 返回成员属性内存地址相对于对象内存地址的偏移量，通过该方法可以计算一个对象在内存
 * 中的空间大小，方法是通过反射得到它的所有Field(包括父类继承得到的)，找出Field中
 * 偏移量最大值，然后对该最大偏移值填充字节数即为对象大小
 */
public class _2_objectFieldOffset {

}
