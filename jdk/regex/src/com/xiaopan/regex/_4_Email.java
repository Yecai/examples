package com.xiaopan.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class _4_Email {
    public static void main(String[] args) throws Exception { 
//        String input = ".hello@xiaopan.com";
//        String input = "@hello@xiaopan.com";
//        String input = "www.hello@xiaopan.com";
        String input = "he_l'l\"o@xiaopan.com";
        
        // �������� EMAIL ��ַ�Ƿ��� �Ƿ�����"."��"@"��Ϊ��ʼ�ַ�      
        Pattern p = Pattern.compile("^\\.|^\\@"); 
        Matcher m = p.matcher(input); 
        if (m.find()){ 
          System.err.println("EMAIL ��ַ������'.'��'@'��Ϊ��ʼ�ַ�"); 
        } 
        // ����Ƿ���"www."Ϊ��ʼ
        p = Pattern.compile("^www\\."); 
        m = p.matcher(input); 
        if (m.find()) { 
          System.out.println("EMAIL ��ַ������'www.'��ʼ"); 
        } 
        // ����Ƿ�����Ƿ��ַ�
        p = Pattern.compile("['\"~#]+"); 
        m = p.matcher(input); 
        StringBuffer sb = new StringBuffer(); 
        boolean result = m.find(); 
        boolean deletedIllegalChars = false; 
        while(result) { 
           // ����ҵ��˷Ƿ��ַ���ô�����±��
           deletedIllegalChars = true; 
           // �����������Ƿ��ַ���ð��˫���ŵȣ���ô�Ͱ�������ȥ���ӵ� SB ����
           m.appendReplacement(sb, ""); 
           result = m.find(); 
        } 
        m.appendTail(sb); 
        if (deletedIllegalChars) { 
            System.out.println("����� EMAIL ��ַ�������ð�š����ŵȷǷ��ַ������޸�"); 
            System.out.println("�����ڵ�����Ϊ : "+input); 
            System.out.println("�޸ĺ�Ϸ��ĵ�ַӦ���� : "+sb.toString()); 
       } 
     }
}
