package com.xiaopan.regex.demo;

public class _2_Chinese {
    public static void main(String[] args) {
        System.out.println("fdaÎÒfds".matches(".*[\u4e00-\u9fa5]+.*"));
    }
}
