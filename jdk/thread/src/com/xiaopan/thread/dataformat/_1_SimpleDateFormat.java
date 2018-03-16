package com.xiaopan.thread.dataformat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Package: com.xiaopan.thread.demo
 * @ClassName: _1_SimpleDateFormat
 * @date: 2018年3月16日 下午6:44:25
 * @Description: http://www.cnblogs.com/peida/archive/2013/05/31/3070790.html
 */
public class _1_SimpleDateFormat {
    public static void main(String[] args) {
        for(int i = 0; i < 3; i++){
            new TestSimpleDateFormatThreadSafe().start();
        }
    }
    
    public static class TestSimpleDateFormatThreadSafe extends Thread {
        @Override
        public void run() {
            while(true) {
                try {
                    this.join(2000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                try {
                    System.out.println(this.getName()+":"+ DateUtil.parse("2018-03-16 18:45:00"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }    
    }
}

class DateUtil {
    private static final  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    public static  String formatDate(Date date)throws ParseException{
        return sdf.format(date);
    }
    
    public static Date parse(String strDate) throws ParseException{

        return sdf.parse(strDate);
    }
    
}
