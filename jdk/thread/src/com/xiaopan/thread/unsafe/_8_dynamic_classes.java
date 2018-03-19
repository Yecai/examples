package com.xiaopan.thread.unsafe;

import java.io.File;
import java.io.FileInputStream;

import sun.misc.Unsafe;

/**
 * @Package: com.xiaopan.thread.unsafe
 * @ClassName: _8_dynamic_classes
 * @date: 2018年3月19日 下午9:03:25
 * @Description: 
 * 我们可以在运行时创建一个类，比如从已编译的.class文件中。
 * 将类内容读取为字节数组，并正确地传递给defineClass方法。
 */
public class _8_dynamic_classes {
    public static void main(String[] args) {
        byte[] classContents = getClassContent();
        Class c = getUnsafe().defineClass(
                      null, classContents, 0, classContents.length);
            c.getMethod("a").invoke(c.newInstance(), null); // 1
    }
    
    /**
     * 从定义文件（class文件）中读取（代码）如下：
     */
    private static byte[] getClassContent() throws Exception {
        File f = new File("D:\\examples\\jdk\\thread\\bin\\com\\xiaopan\\thread\\unsafe\\A.class");
        FileInputStream input = new FileInputStream(f);
        byte[] content = new byte[(int)f.length()];
        input.read(content);
        input.close();
        return content;
    }
    
    public static Unsafe getUnsafe() {
        return _1_GetUnsafe.getUnsafe();
    }
}
