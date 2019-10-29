package com.shsxt.crm.base.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 时间日期格式转换工具类
 */
public class DateUtil {

    public static String pattern_date = "yyyy/MM/dd";

    public static String getDateStr(LocalDateTime date, String pattern) {
        // JDK1.7
        //SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        //return sdf.format(new Date());

        // JDK1.8
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
        return dtf.format(date);
    }

    public static void main(String[] args) {
        String dateStr = DateUtil.getDateStr(LocalDateTime.now(), DateUtil.pattern_date);
        System.out.println(dateStr);
    }

}

