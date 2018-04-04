package com.xiaopan.time;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;

public class _2_LocalDate_LocalTime {
    public static void main(String[] args) {
        //LocalDate��ʾ����ʱ�������ڣ�����1-1-2000��LocalTime��ʾ����ʱ����ʱ�䣬����04:44:50.12
        
        //��ȡLocalDate
        LocalDate localDate = LocalDate.now();
        LocalDate localDate1 = LocalDate.ofYearDay(2005, 86);//2005���86��
        LocalDate localDate2 = LocalDate.of(2013, Month.AUGUST, 10); //2013��8��10��
        
        LocalTime localTime = LocalTime.of(22, 33); //10:33 PM
        LocalTime localTime2 = LocalTime.now();
        LocalTime localTime3 = LocalTime.ofSecondOfDay(4503); //����һ���е�4503�루1:15:30 AM��
        
        //LocalDate��LocalTime��Instantһ����ʵ���ǲ��ɱ��
        
        //�Ӽ���Ƚ�
        LocalDate localDate3 = localDate.plus(5, ChronoUnit.HOURS);
        localDate3.isBefore(localDate);
    }
}
