package com.xiaopan.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class _3_GroupTest {
    @SuppressWarnings("unused")
    public static void main(String[] args) throws Exception {
        Pattern p = Pattern.compile("(ca)(t)");
        Matcher m = p.matcher("one cat,two cats in the yard");
//        StringBuffer sb = new StringBuffer();
        boolean result = m.find();
        System.out.println("该次查找获得匹配组的数量为：" + m.groupCount());
        for (int i = 1; i <= m.groupCount(); i++) {
            System.out.println("第" + i + "组的子串内容为： " + m.group(i));
        }
    }
}
