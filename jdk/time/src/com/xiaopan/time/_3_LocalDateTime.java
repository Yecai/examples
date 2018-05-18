package com.xiaopan.time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class _3_LocalDateTime {
    public static void main(String[] args) {
      //LocalDateTime是LocalDate和LocalTime的组合体，表示的是不带时区的日期及时间
        LocalDateTime localDateTime = LocalDateTime.now();
        
        //当前时间加上25小时3分钟
        LocalDateTime inTheFuture = localDateTime.plusHours(25).plusMinutes(3);
        //同样可以用在localTime和localDate中
        
        
        LocalDateTime todayEnd = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
    }
}
