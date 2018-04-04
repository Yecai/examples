package com.xiaopan.time;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;

public class _2_LocalDate_LocalTime {
    public static void main(String[] args) {
        //LocalDate表示不带时区的日期，比如1-1-2000。LocalTime表示不带时区的时间，比如04:44:50.12
        
        //获取LocalDate
        LocalDate localDate = LocalDate.now();
        LocalDate localDate1 = LocalDate.ofYearDay(2005, 86);//2005年第86天
        LocalDate localDate2 = LocalDate.of(2013, Month.AUGUST, 10); //2013年8月10日
        
        LocalTime localTime = LocalTime.of(22, 33); //10:33 PM
        LocalTime localTime2 = LocalTime.now();
        LocalTime localTime3 = LocalTime.ofSecondOfDay(4503); //返回一天中第4503秒（1:15:30 AM）
        
        //LocalDate和LocalTime和Instant一样，实例是不可变的
        
        //加减与比较
        LocalDate localDate3 = localDate.plus(5, ChronoUnit.HOURS);
        localDate3.isBefore(localDate);
    }
}
