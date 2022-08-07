package com.hilton.hibye.global.Utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    public static LocalDateTime getToday() {
        LocalDateTime now = LocalDateTime.now();
        return LocalDateTime.of(
                now.getYear(),
                now.getMonth(),
                now.getDayOfMonth(),
                0, 0, 0
        );
    }

    public static LocalDateTime getYesterday() {
        LocalDateTime now = LocalDateTime.now();
        return LocalDateTime.of(
                now.getYear(),
                now.getMonth(),
                now.getDayOfMonth(),
                0, 0, 0
        ).minusDays(1);
    }

    public static LocalDateTime getTomorrow() {
        return DateUtil.getToday().plusDays(1L);
    }

    public static LocalDateTime getCommuteTime() {
        LocalDateTime now = LocalDateTime.now();
        return LocalDateTime.of(
                now.getYear(),
                now.getMonth(),
                now.getDayOfMonth(),
                now.getHour(),
                now.getMinute()
        );
    }

    public static String localDateTimeToStringDate(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.ofPattern("MM-dd"));
    }

    public static String localDateTimeToStringTime(LocalDateTime localDateTime) {
        if (localDateTime == null) return "열심히 근무 중...";
        return localDateTime.format(DateTimeFormatter.ofPattern("hh:mm"));
    }
}
