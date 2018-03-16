package com.xiaopan.regex.demo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class _1_IP {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile(
                "((25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))\\.){3}(25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))");
        Matcher match = pattern.matcher("0.0.0.0");
        System.out.println(match.matches());
//        System.out.println(match.find());
        
        match = pattern.matcher("asdfsad 0.0.0.0 dffads");
//        System.out.println(match.matches());
        System.out.println(match.find());
    }
}
