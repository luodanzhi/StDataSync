/*
 * @(#) DateUtil.java
 * Created Date: 2009-5-16
 *				
 * Copyright (c)  Jiangsu Ecode Co., Ltd
 *
 * This software is the confidential and proprietary information of
 * Jiangsu Ecode Co., Ltd. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in accordance
 * with the terms of the license agreement you entered into with
 * Jiangsu Ecode Co., Ltd.
 */
package com.lazy.st.lib;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期处理工具类
 * 
 */
public class DateUtil {
    /** 注意格里历和儒略历交接时的日期差�?*/
    private static transient int gregorianCutoverYear = 1582;
    public static final String FORMAT_DATE = "yyyy-MM-dd";

    /**
     * 检查传入的参数代表的年份是否为闰年
     * 
     * @param year
     * @return
     */
    public static boolean isLeapYear(int year){
        if(year >= gregorianCutoverYear){
            return (year % 4 == 0) && ((year % 100 != 0) || (year % 400 == 0));
        }

        return (year % 4 == 0);
    }

    /**
     * 取当前日期时间，返回日期格式为yyyy-MM-dd HH:mm:ss
     * 
     * @return
     */
    public static String getCurSysTime(){
        return formatDateTime(new Date());
    }

    /**
     * 将指定日期加一天
     * 
     * @param date
     * @return
     */
    public static Date addOneDay(Date date){
        return addDay(date, 1);
    }

    /**
     * 将指定日期加上指定天数
     * 
     * @param date
     *            指定日期
     * @param dayNum
     *            指定天数
     * @return 返回增加指定天数后的日期
     */
    public static Date addDay(Date date, int dayNum){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_YEAR, dayNum);
        return c.getTime();
    }

    /**
     * 将指定日期加上指定月数
     * 
     * @param date
     *            指定日期
     * @param monthNum
     *            指定月数
     * @return 返回增加指定月数后的日期
     */
    public static Date addMonth(Date date, int monthNum){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, monthNum);
        return c.getTime();
    }

    /**
     * 将指定日期加上指定年数
     * 
     * @param date
     *            指定日期
     * @param yearNum
     *            指定年数
     * @return 返回增加指定年数后的日期
     */
    public static Date addYear(Date date, int yearNum){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.YEAR, yearNum);
        return c.getTime();
    }
    
    /**
     * 取日期所在月份有多少天
     * 
     * @param date
     * @return
     */
    public static int getDaysOneMonth(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.getMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 计算两个日期之间相隔的天数
     * 
     * @param beginDate
     * @param endDate
     * @return
     */
    public static int getDaysBetween(Date beginDate, Date endDate){
        Calendar beginCalendar = Calendar.getInstance();
        Calendar endCalendar = Calendar.getInstance();
        if(beginDate.after(endDate)){
            beginCalendar.setTime(endDate);
            endCalendar.setTime(beginDate);
        }else{
            beginCalendar.setTime(beginDate);
            endCalendar.setTime(endDate);
        }

        int days = endCalendar.get(java.util.Calendar.DAY_OF_YEAR) - beginCalendar.get(java.util.Calendar.DAY_OF_YEAR);
        int endYear = endCalendar.get(java.util.Calendar.YEAR);

        // 如果不是同一年
        if(beginCalendar.get(java.util.Calendar.YEAR) != endYear){
            do{
                days += beginCalendar.getActualMaximum(java.util.Calendar.DAY_OF_YEAR);
                beginCalendar.add(java.util.Calendar.YEAR, 1);
            }while(beginCalendar.get(java.util.Calendar.YEAR) != endYear);
        }

        return days;
    }

    /**
     * 将指定日期格式化成yyyy-mm-dd格式的字符串输出
     * 
     * @param date
     * @return
     */
    public static String formatDate(Date date){
        String str = formatDate(date, "yyyy-MM-dd");
        return str;
    }

    /**
     * 将指定日期格式化成yyyy-mm-dd HH:mm:ss格式的字符串输出
     * 
     * @param date
     * @return
     */
    public static String formatDateTime(Date date){
        return formatDate(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 将指定日期格式化成指定格式的字符串输出
     * 
     * @param date
     * @param format
     * @return
     */
    public static String formatDate(Date date, String format){
        if(date == null){
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(format); // 格式化当前系统日期
        String str = dateFormat.format(date);
        return str;
    }

    /**
     * 解析yyyy-MM-dd HH:mm:ss 格式的日期字符串为Date类型，字符串日期参数的格式不正确时，解析异常，返回null
     * 
     * @param dateStr
     * @return
     */
    public static Date parseStrDateTime(String dateStr){
        return parseStrDate(dateStr, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 解析yyyy-MM-dd HH:mm格式的日期字符串为Date类型，字符串日期参数的格式不正确时，解析异常，返回null
     * 
     * @param dateStr
     * @return
     */
    public static Date parseStrDateHM(String dateStr){
        return parseStrDate(dateStr, "yyyy-MM-dd HH:mm");
    }

    /**
     * 解析 yyyy-MM-dd HH 格式的日期字符串为Date类型，字符串日期参数的格式不正确时，解析异常，返回null
     * 
     * @param dateStr
     * @return
     */
    public static Date parseStrDateH(String dateStr){
        return parseStrDate(dateStr, "yyyy-MM-dd HH");
    }

    /**
     * 解析yyyy-MM-dd格式的日期字符串为Date类型，字符串日期参数的格式不正确时，解析异常，返回null
     * 
     * @param dateStr
     * @return
     */
    public static Date parseStrDate(String dateStr){
        return parseStrDate(dateStr, "yyyy-MM-dd");
    }

    /**
     * 解析指定格式的日期字符串为Date类型，字符串日期格式与指定的格式不匹配时，解析异常，返回null
     * 
     * @param dateStr
     * @param format
     * @return
     */
    public static Date parseStrDate(String dateStr, String format){
        try{
            return stringToDate(dateStr, format);
        }catch(ParseException e){
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * 解析指定格式的日期字符串为Date类型，字符串日期格式与指定的格式不匹配时，解析异常
     * @param dateStr
     * @param format
     * @return
     * @throws ParseException
     *
     * @author huangzw
     * @date 2013-5-30 下午3:01:24
     */
    public static Date stringToDate(String dateStr, String format) throws ParseException{
        if(dateStr == null){
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(format); // 格式化当前系统日期
        return dateFormat.parse(dateStr);
    }

    public static Date setTimeOfDate(Date date, int hours, int minute, int second){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, hours);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, second);
        return c.getTime();
    }

    public static Calendar convertDate(Date date){
        if(date == null){
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        return c;
    }
    
    /**
     * 取当前月所在季度的开始日期与结束日期
     * @return 返回数组，array[0]为开始日期，array[1]为开始日期，返回的日期为yyyyMMdd格式的字符串
     *
     * @author huangzw
     * @date 2013-5-30 下午2:50:55
     */
    public static String[] getCurQuarterBeginEndDate(){
        String today = formatDate(new Date(), "yyyyMMdd");
        String year = today.substring(0, 4);
        int month = Integer.parseInt(today.substring(4, 6));
        String[] result = new String[2];
       
        if(month == 1 || month == 2 || month == 3){
            result[0] = year + "0101";
            result[1] = year + "0331";
        }else if(month == 4 || month == 5 || month == 6){
            result[0] = year + "0401";
            result[1] = year + "0630";
        }else if(month == 7 || month == 8 || month == 9){
            result[0] = year + "0701";
            result[1] = year + "0930";
        }else{
            result[0] = year + "1001";
            result[1] = year + "1231";
        }

        return result;
    }
    
    public static boolean isSameDay(Date date1, Date date2){
        String d1 = formatDate(date1, FORMAT_DATE);
        String d2 = formatDate(date2, FORMAT_DATE);
        return d1.equals(d2);
    }

    public static void main(String[] args){
        // Calendar c1 = Calendar.getInstance();
        // Calendar c2 = Calendar.getInstance();
        // / c1.set(2008, (11 - 1), 28); // 2008-11-28
        // c2.set(2009, (5 - 1), 18); // 2009-2-28
        // System.out.println(addOneDay(c1.getTime()));
        // System.out.println(formatDate(c2.getTime()));
        // System.out.println(DateUtil.formatDate(new Date(), "yyyyMMddHHmmss"));

        Date today = new Date();
        System.out.println(DateUtil.formatDate(today, "yyyyMMddHHmmssSSS"));
//        System.out.println(DateUtil.formatDate(addMonth(today, -2), "yyyy-MM-dd HH:mm:ss"));
//        System.out.println(DateUtil.formatDate(addYear(today, -25), "yyyy-MM-dd"));
//        System.out.println(DateUtil.formatDate(addYear(today, -30), "yyyy-MM-dd"));
//        System.out.println(DateUtil.formatDate(addYear(today, -35), "yyyy-MM-dd"));
//        System.out.println(DateUtil.formatDate(addYear(today, -40), "yyyy-MM-dd"));
//        System.out.println(DateUtil.formatDate(addYear(today, -45), "yyyy-MM-dd"));
//        System.out.println(DateUtil.formatDate(addYear(today, -50), "yyyy-MM-dd"));
//        System.out.println(DateUtil.formatDate(addYear(today, -55), "yyyy-MM-dd"));
    }
}
