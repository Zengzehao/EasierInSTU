package com.example.zengzehao.messageshare.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zengzehao on 16-11-26.
 */

public class ConutDate {
    public static String conutTwoDate(Date date1,Date date2){
        long seconds = date1.getTime() - date2.getTime();
        long day = seconds / (60*60*24*1000);
        long hour = seconds/ (60*60*1000);
        long minute = seconds /(60*1000);
        if( day > 7){
            return new SimpleDateFormat("yyyy-MM-dd").format(date2);
        }else if(day >0 && day <= 7){
            return day+"天前";
        }else if(hour>0 && hour <= 24) {
            return hour+"小时前";
        }else if(minute>0 && minute <=60 ){
            return minute+"分钟前";
        }else{
            return "刚刚";
        }

    }


}
