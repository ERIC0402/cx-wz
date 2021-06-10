package com.cx.wz.uitls;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtils {
    public static ThreadLocal<SimpleDateFormat> DATETIME_FORMAT = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    public static ThreadLocal<SimpleDateFormat> DATE_FORMAT = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));

    public static Date getTimeOfDate(Date datetime) {
        try {
            return Date.from(DATE_FORMAT.get().parse(DATE_FORMAT.get().format(datetime)).toInstant());
        } catch (ParseException e) {
            return null;
        }
    }
}
