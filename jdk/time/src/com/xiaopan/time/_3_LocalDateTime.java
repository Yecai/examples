package com.xiaopan.time;

import java.time.LocalDateTime;

public class _3_LocalDateTime {
    public static void main(String[] args) {
      //LocalDateTime��LocalDate��LocalTime������壬��ʾ���ǲ���ʱ�������ڼ�ʱ��
        LocalDateTime localDateTime = LocalDateTime.now();
        
        //��ǰʱ�����25Сʱ3����
        LocalDateTime inTheFuture = localDateTime.plusHours(25).plusMinutes(3);
        //ͬ����������localTime��localDate��
        
    }
}
