package com.lamp.app.test;


import javax.sql.rowset.serial.SerialException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;


public class Test {
    public static void main(String[] args) throws ParseException {
        /* LocalDate now = LocalDate.now();
        System.out.println("now 날짜 = " + now);

        DateTimeFormatter fomatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
        String formatternow = fomatter.format(now);
        System.out.println("formatternow = " + formatternow); */

        /* Calendar car = Calendar.getInstance();
        System.out.println("getTime = " + car.getTime());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String sDate = sdf.format(car.getTime());
        System.out.println("sDate = " + sDate);

        String sNumberLength = "000" */

        /*
        // long -> Date
        long ldate1 = 1427790487, ldate2;
        String dateFormatStringTime;
        ldate1 = ldate1*1000;

        System.out.println("ldate1 = " + ldate1);
        System.out.println(((Object)ldate1).getClass().getName());
        System.out.println(((Object)ldate1).getClass().getSimpleName());

        Date date1 = new Date(ldate1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        dateFormatStringTime = sdf.format(date1);
        System.out.println("------------------------------------------");
        System.out.println(dateFormatStringTime);

        // Date -> long
        Date date2 = sdf.parse(dateFormatStringTime);
        System.out.println("String->date : " + date2);

        ldate2 = date2.getTime(); // date -> Long
        System.out.println(ldate2);

        ldate2 = ldate2 / 1000;
        System.out.println("원래 ldate2 : " + ldate2);
        System.out.println(((Object) ldate2).getClass().getName()); */

        /*
        Date date = new Date();
        System.out.println("date = " + date.getTime());
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        String ymd = sdf2.format(date);
        System.out.println("ymd = " + ymd);
        date = sdf2.parse(ymd);
        System.out.println("ymd.getTime = " + date.getTime()); */

//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//        Date date1;
//        Date date2;
        /*
        for(int i=1; i<=5; i++) {
            if(i==5) break;
            date1 = sdf.parse("" + 20221227 + (i-1));
            date2 = sdf.parse("" + 20221227 + i);
            long no1 = date1.getTime();
            long no2 = date2.getTime();
            System.out.println("차이값 (" + (i+1) +") - (" + i + ")= " + (no2 - no1));
        }*/

//        for(int i=1; i<=5; i++) {
//            date1 = sdf.parse("" + 20221227 + (i - 1));
//            long no1 = date1.getTime() / 100000;
//            System.out.println("date" + i + " = " + ((no1 / 864)%10));
//        }

//        Date date = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//        String date_str = sdf.format(date);
//        Date date2 = sdf.parse(date_str);
//        Date date3 = sdf.parse("20230116");
//        Date date4 = sdf.parse("20230117");
//        Date date5 = sdf.parse("20230118");
//
//        System.out.println("date = " + date.getTime());
//        System.out.println("date2 = " + date2.getTime());
//        System.out.println("date3 = " + date3.getTime());
//        System.out.println("date4 = " + date4.getTime());
//        System.out.println("date5 = " + date5.getTime());
//        System.out.println("===============================");
//        System.out.println("date3-2 = " + (date3.getTime()/86400000 - date2.getTime()/86400000));
//        System.out.println("date4-3 = " + (date4.getTime()/86400000 - date3.getTime()/86400000));
//        System.out.println("date5-4 = " + (date5.getTime()/86400000 - date4.getTime()/86400000));
//        System.out.println((int)(1.9));
//        System.out.println(1990 + (int)(Math.random() * 24));
        LocalDate localDate = LocalDate.now();
        int lastYear = localDate.getYear() - 1;
        System.out.println(lastYear);
    }
}
