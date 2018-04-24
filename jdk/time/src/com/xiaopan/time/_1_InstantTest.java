package com.xiaopan.time;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class _1_InstantTest {
    public static void main(String[] args) {
        //当前时间
        Instant instant = Instant.now();
        System.out.println(instant);
        
        //Data转Instant
        Instant instant1 = Instant.ofEpochMilli(new Date().getTime());
        System.out.println(instant1);
        
        //字符串转Instant
        //Instant代表的是一个时间，并不包括时区的概念，所以必须传入的是符合UTC格式的字符串
        Instant instant2 = Instant.parse("1995-10-23T10:12:35.00Z");
        System.out.println(instant2);
        
        //时间运算
        Instant instant3 = instant2.plus(Duration.ofHours(5)).plusMillis(4);
        //Instant每次运算产生新实例
        System.out.println(instant2 == instant3);
        
        //时间运算，以下两种方式等价
        instant3.minus(5, ChronoUnit.DAYS); //方式1
        instant3.minus(Duration.ofDays(5)); //方式2
        
        //计算两个Instant之间的分钟数
        long diffAsMinutes = ChronoUnit.MINUTES.between(instant, instant2);
        
        //Instant比较
        instant1.compareTo(instant3);
        instant1.isAfter(instant2);
        instant1.isBefore(instant2);
        
        
        
    }
}
