package com.xiaopan.regex;

import java.util.regex.Pattern;

/**
 * @Package: com.xiaopan.regex
 * @ClassName: _1_Replacement
 * @date: 2018��3��16�� ����11:51:42
 * @Description: https://www.ibm.com/developerworks/cn/java/l-regp/part2/
 */
public class _1_Replacement {
    public static void main(String[] args) throws Exception {
        // ����һ�� Pattern, ͬʱ����һ��������ʽ
        Pattern p = Pattern.compile("[/]+");
        // �� Pattern �� split() �������ַ�����"/"�ָ�
        String[] result = p.split("Kevin has seen �� LEON �� seveal times,because it is a good film."
                + "/ �����Ѿ����������ɱ�ֲ�̫�䡷�����ˣ���Ϊ����һ��" + "�õ�Ӱ��/ ���� : ���ġ�");
        // String[] result = p.split(
        // "Kevin has seen �� LEON �� seveal times,because it is a good film."
        // +"/ �����Ѿ����������ɱ�ֲ�̫�䡷�����ˣ���Ϊ����һ��"
        // +"�õ�Ӱ��/ ���� : ���ġ�", 2);
        for (int i = 0; i < result.length; i++)
            System.out.println(result[i]);
    }
}
