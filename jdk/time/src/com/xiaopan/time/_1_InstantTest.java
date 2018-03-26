package com.xiaopan.time;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class _1_InstantTest {
    public static void main(String[] args) {
        //��ǰʱ��
        Instant instant = Instant.now();
        System.out.println(instant);
        
        //DataתInstant
        Instant instant1 = Instant.ofEpochMilli(new Date().getTime());
        System.out.println(instant1);
        
        //�ַ���תInstant
        //Instant�������һ��ʱ�䣬��������ʱ���ĸ�����Ա��봫����Ƿ���UTC��ʽ���ַ���
        Instant instant2 = Instant.parse("1995-10-23T10:12:35.00Z");
        System.out.println(instant2);
        
        //ʱ������
        Instant instant3 = instant2.plus(Duration.ofHours(5)).plusMillis(4);
        //Instantÿ�����������ʵ��
        System.out.println(instant2 == instant3);
        
        //ʱ�����㣬�������ַ�ʽ�ȼ�
        instant3.minus(5, ChronoUnit.DAYS); //��ʽ1
        instant3.minus(Duration.ofDays(5)); //��ʽ2
        
        //��������Instant֮��ķ�����
        long diffAsMinutes = ChronoUnit.MINUTES.between(instant, instant2);
        
        //Instant�Ƚ�
        instant1.compareTo(instant3);
        instant1.isAfter(instant2);
        instant1.isBefore(instant2);
        
        
        
    }
}
