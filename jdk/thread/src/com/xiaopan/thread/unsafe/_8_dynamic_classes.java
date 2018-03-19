package com.xiaopan.thread.unsafe;

import java.io.File;
import java.io.FileInputStream;

import sun.misc.Unsafe;

/**
 * @Package: com.xiaopan.thread.unsafe
 * @ClassName: _8_dynamic_classes
 * @date: 2018��3��19�� ����9:03:25
 * @Description: 
 * ���ǿ���������ʱ����һ���࣬������ѱ����.class�ļ��С�
 * �������ݶ�ȡΪ�ֽ����飬����ȷ�ش��ݸ�defineClass������
 */
public class _8_dynamic_classes {
    public static void main(String[] args) {
        byte[] classContents = getClassContent();
        Class c = getUnsafe().defineClass(
                      null, classContents, 0, classContents.length);
            c.getMethod("a").invoke(c.newInstance(), null); // 1
    }
    
    /**
     * �Ӷ����ļ���class�ļ����ж�ȡ�����룩���£�
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
