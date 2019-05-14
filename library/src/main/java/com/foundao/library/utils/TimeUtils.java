package com.foundao.library.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 时间工具类
 */
public class TimeUtils {
    private static final DateFormat DEFAULT_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
    private static final DateFormat HOUR_FORMAT = new SimpleDateFormat("HH:mm", Locale.getDefault());
    private static final DateFormat YMD_FORMAT = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

    /**
     * 将时间戳转为时间字符串
     * 格式为：yyyy-MM-dd HH:mm:ss
     */
    public static String millis2String(long millis) {
        return millis2String(millis, DEFAULT_FORMAT);
    }

    public static String millis2HourString(long millis) {
        return millis2String(millis, HOUR_FORMAT);
    }

    /**
     * 将时间戳转为时间字符串
     * 格式为：format
     */
    public static String millis2String(long millis, DateFormat format) {
        return format.format(new Date(millis));
    }

    /**
     * 将时间戳转为时间字符串
     * 格式为：format
     */
    public static String millis2StringYMD(long millis) {
        return millis2String(millis, YMD_FORMAT);
    }

    /**
     * 将时间字符串转为时间戳
     * time格式为yyyy-MM-dd HH:mm:ss
     */
    public static long string2Millis(String time) {
        return string2Millis(time, DEFAULT_FORMAT);
    }

    /**
     * 将时间字符串转为时间戳
     * time格式为format
     */
    public static long string2Millis(String time, final DateFormat format) {
        try {
            return format.parse(time).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 判断某个时间是否在今天，直接取了一年的第几天，如果在不同的年会出问题
     *
     * @param time
     * @return
     */
    public static boolean isInToday(long time) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(time);
        return instance.get(Calendar.DAY_OF_YEAR) == Calendar.getInstance().get(Calendar.DAY_OF_YEAR);
    }
}
