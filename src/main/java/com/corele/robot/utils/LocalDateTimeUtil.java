package com.corele.robot.utils;

import org.apache.commons.lang3.StringUtils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * @author jun
 */
public class LocalDateTimeUtil {

    public static LocalDateTime parseForStr(String str,String pattern){
        if (StringUtils.isEmpty(str)){
            throw new IllegalArgumentException("The string is null for date");
        }
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.parse(str, dateFormatter);
    }

    /**
     * 格式化指定格式
     * @param localDateTime
     * @param pattern
     * @return
     */
    public static String format(LocalDateTime localDateTime,String pattern){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        return dateTimeFormatter.format(localDateTime);
    }

    public static String formatDefault(LocalDateTime localDateTime){
        return format(localDateTime,"yyyy-MM-dd HH:mm:ss");
    }

    public static String format(LocalDate localDate,String pattern){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        return dateTimeFormatter.format(localDate);
    }

    public static LocalDateTime timestampToLocalDateTime(long timestamp){
        Instant instant = Instant.ofEpochMilli(timestamp);
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }
}
