package com.xiaopan.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class _2_MatcherTest {
    public static void main(String[] args) throws Exception {
        // ���� Pattern �����ұ���һ���򵥵�������ʽ"Kelvin"
        Pattern p = Pattern.compile("Kelvin");
        // �� Pattern ��� matcher() ��������һ�� Matcher ����
        Matcher m = p.matcher("Kelvin Li and Kelvin Chan are both working in Kelvin Chen's KelvinSoftShop company");
        StringBuffer sb = new StringBuffer();
        int i = 0;
        // ʹ�� find() �������ҵ�һ��ƥ��Ķ���
        boolean result = m.find();
        // ʹ��ѭ�������������е� kelvin �ҳ����滻�ٽ����ݼӵ� sb ��
        while (result) {
            i++;
            m.appendReplacement(sb, "Kevin");
            System.out.println("��" + i + "��ƥ��� sb �������ǣ�" + sb);
            // ����������һ��ƥ�����
            result = m.find();
        }
        // ������ appendTail() ���������һ��ƥ����ʣ���ַ����ӵ� sb �
        m.appendTail(sb);
        System.out.println("���� m.appendTail(sb) �� sb ������������ :" + sb.toString());
    }
}
