package com.xiaopan.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class _4_Email {
    public static void main(String[] args) throws Exception { 
//        String input = ".hello@xiaopan.com";
//        String input = "@hello@xiaopan.com";
//        String input = "www.hello@xiaopan.com";
        String input = "he_l'l\"o@xiaopan.com";
        
        // 检测输入的 EMAIL 地址是否以 非法符号"."或"@"作为起始字符      
        Pattern p = Pattern.compile("^\\.|^\\@"); 
        Matcher m = p.matcher(input); 
        if (m.find()){ 
          System.err.println("EMAIL 地址不能以'.'或'@'作为起始字符"); 
        } 
        // 检测是否以"www."为起始
        p = Pattern.compile("^www\\."); 
        m = p.matcher(input); 
        if (m.find()) { 
          System.out.println("EMAIL 地址不能以'www.'起始"); 
        } 
        // 检测是否包含非法字符
        p = Pattern.compile("['\"~#]+"); 
        m = p.matcher(input); 
        StringBuffer sb = new StringBuffer(); 
        boolean result = m.find(); 
        boolean deletedIllegalChars = false; 
        while(result) { 
           // 如果找到了非法字符那么就设下标记
           deletedIllegalChars = true; 
           // 如果里面包含非法字符如冒号双引号等，那么就把他们消去，加到 SB 里面
           m.appendReplacement(sb, ""); 
           result = m.find(); 
        } 
        m.appendTail(sb); 
        if (deletedIllegalChars) { 
            System.out.println("输入的 EMAIL 地址里包含有冒号、逗号等非法字符，请修改"); 
            System.out.println("您现在的输入为 : "+input); 
            System.out.println("修改后合法的地址应类似 : "+sb.toString()); 
       } 
     }
}
