package com.xiaopan.regex;

import java.util.regex.Pattern;

/**
 * @Package: com.xiaopan.regex
 * @ClassName: _1_Replacement
 * @date: 2018年3月16日 上午11:51:42
 * @Description: https://www.ibm.com/developerworks/cn/java/l-regp/part2/
 */
public class _1_Replacement {
    public static void main(String[] args) throws Exception {
        // 生成一个 Pattern, 同时编译一个正则表达式
        Pattern p = Pattern.compile("[/]+");
        // 用 Pattern 的 split() 方法把字符串按"/"分割
        String[] result = p.split("Kevin has seen 《 LEON 》 seveal times,because it is a good film."
                + "/ 凯文已经看过《这个杀手不太冷》几次了，因为它是一部" + "好电影。/ 名词 : 凯文。");
        // String[] result = p.split(
        // "Kevin has seen 《 LEON 》 seveal times,because it is a good film."
        // +"/ 凯文已经看过《这个杀手不太冷》几次了，因为它是一部"
        // +"好电影。/ 名词 : 凯文。", 2);
        for (int i = 0; i < result.length; i++)
            System.out.println(result[i]);
    }
}
