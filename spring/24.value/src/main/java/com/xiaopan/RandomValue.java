package com.xiaopan;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RandomValue implements CommandLineRunner{

    @Value("${my.secret}")
    private String secret;
    @Value("${my.number}")
    private int number;
    @Value("${my.bignumber}")
    private long bignumber;
    @Value("${my.uuid}")
    private String uuid;
    @Value("${my.number.less.than.ten}")
    private int lessThanTen;
    @Value("${my.number.in.range}")
    private int range;
    
    
    @Override
    public void run(String... args) throws Exception {
        System.out.println("");
        System.out.println("Random values:");
        System.out.println("secret : " + secret);
        System.out.println("number : " + number);
        System.out.println("bignumber : " + bignumber);
        System.out.println("uuid : " + uuid);
        System.out.println("lessThanTen : " + lessThanTen);
        System.out.println("range : " + range);
    }

}
