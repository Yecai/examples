package com.xiaopan.thread.unsafe;

/**
 * @Package: com.xiaopan.thread.unsafe
 * @ClassName: _10_Serializable
 * @date: 2018年3月19日 下午9:35:30
 * @Description: 快速序列化
 * 每个人都知道java标准的序列化的功能速度很慢而且它还需要类拥有公有的构造函数。

外部序列化是更好的方式，但是需要定义针对待序列化类的schema。

非常流行的高性能序列化库，像kryo是有使用限制的，比如在内存缺乏的环境就不合适。

但通过使用Unsafe类我们可以非常简单的实现完整的序列化功能。

序列化：

通过反射定义类的序列化。 这个可以只做一次。
通过Unsafe的getLong, getInt, getObject等方法获取字段真实的值。
添加可以恢复该对象的标识符。
将这些数据写入到输出
当然也可以使用压缩来节省空间。

反序列化:

创建一个序列化类的实例，可以通过方法allocateInstance。因为该方法不需要任何构造方法。
创建schama, 和序列化类似
从文件或输入读取或有的字段
使用 Unsafe 的 putLong, putInt, putObject等方法来填充对象。
Actually, there are much more details in correct inplementation, but intuition is clear.

事实上要正确实现序列化和反序列化需要注意很多细节，但是思路是清晰的。

这种序列化方式是非常快的。

顺便说一句，在 kryo 有许多使用Unsafe的尝试 http://code.google.com/p/kryo/issues/detail?id=75
 */
public class _10_Serializable {

}
