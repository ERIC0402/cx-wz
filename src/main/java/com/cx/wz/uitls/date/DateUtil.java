package com.cx.wz.uitls.date;

import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/*
 * <p>
 * All letters 'A' to 'Z' and 'a' to 'z' are reserved as pattern letters. The
 * following pattern letters are defined:
 * <pre>
 *  Symbol  Meaning                     Presentation      Examples
 *  ------  -------                     ------------      -------
 *   G       era                         text              AD; Anno Domini; A
 *   u       year                        year              2004; 04
 *   y       year-of-era                 year              2004; 04
 *   D       day-of-year                 number            189
 *   M/L     month-of-year               number/text       7; 07; Jul; July; J
 *   d       day-of-month                number            10
 *
 *   Q/q     quarter-of-year             number/text       3; 03; Q3; 3rd quarter
 *   Y       week-based-year             year              1996; 96
 *   w       week-of-week-based-year     number            27
 *   W       week-of-month               number            4
 *   E       day-of-week                 text              Tue; Tuesday; T
 *   e/c     localized day-of-week       number/text       2; 02; Tue; Tuesday; T
 *   F       week-of-month               number            3
 *
 *   a       am-pm-of-day                text              PM
 *   h       clock-hour-of-am-pm (1-12)  number            12
 *   K       hour-of-am-pm (0-11)        number            0
 *   k       clock-hour-of-am-pm (1-24)  number            0
 *
 *   H       hour-of-day (0-23)          number            0
 *   m       minute-of-hour              number            30
 *   s       second-of-minute            number            55
 *   S       fraction-of-second          fraction          978
 *   A       milli-of-day                number            1234
 *   n       nano-of-second              number            987654321
 *   N       nano-of-day                 number            1234000000
 *
 *   V       time-zone ID                zone-id           America/Los_Angeles; Z; -08:30
 *   z       time-zone name              zone-name         Pacific Standard Time; PST
 *   O       localized zone-offset       offset-O          GMT+8; GMT+08:00; UTC-08:00;
 *   X       zone-offset 'Z' for zero    offset-X          Z; -08; -0830; -08:30; -083015; -08:30:15;
 *   x       zone-offset                 offset-x          +0000; -08; -0830; -08:30; -083015; -08:30:15;
 *   Z       zone-offset                 offset-Z          +0000; -0800; -08:00;
 *
 *   p       pad next                    pad modifier      1
 *
 *   '       escape for text             delimiter
 *   ''      single quote                literal           '
 *   [       optional section start
 *   ]       optional section end
 *   #       reserved for future use
 *   {       reserved for future use
 *   }       reserved for future use
 * </pre>
 */
public class DateUtil {

    public static String genStrWithPattern(String pattern) {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern));
    }

    public static boolean isSameDay(Date first, Date second) {
        return DateTimeComparator.getDateOnlyInstance().compare(first, second) == 0;
    }

    public static boolean isSame(Date source, Date target) {
        return DateTimeComparator.getInstance().compare(source, target) == 0;
    }

    public static boolean isAfter(Date source, Date target) {
        return DateTimeComparator.getInstance().compare(source, target) == 1;
    }

    public static boolean isBefore(Date source, Date target) {
        return DateTimeComparator.getInstance().compare(source, target) == -1;
    }

    public static long daysBetween(Date start, Date end) {
        return Days.daysBetween(new LocalDate(start.getTime()), new LocalDate(end.getTime())).getDays();
    }

    public static long hoursBetween(Date start, Date end) {
        return Hours.hoursBetween(new org.joda.time.LocalDateTime(start.getTime()), new org.joda.time.LocalDateTime(end.getTime())).getHours();
    }

    public static double minsBetween(Date start, Date end) {
        long l = end.getTime()-start.getTime();
        BigDecimal diff = new BigDecimal(l).divide(new BigDecimal(1000 * 60), 2, RoundingMode.HALF_UP);
        return diff.doubleValue();

    }

    public static int yearsBetween(Date start, Date end) {
        return Years.yearsBetween(new LocalDate(start.getTime()), new LocalDate(end.getTime())).getYears();
    }

    public static Date getTodayMoring() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        return  cal.getTime();
    }

    /**
     * date 增加days天
     *
     * @param date
     * @return
     */
    public static Date plusDays(Date date, long days) {
        if (date == null) {
            return null;
        }
        LocalDateTime dueDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        return Date.from(dueDateTime
                .plusDays(days)
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }

    /**
     * date 减少days天
     *
     * @param date
     * @return
     */
    public static Date minusDays(Date date, long days) {
        if (date == null) {
            return null;
        }
        LocalDateTime dueDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        return Date.from(dueDateTime
                .minusDays(days)
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }

    /**
     * 加上固定小时，有加法即有减法，
     * 除非Long.Min_Value这种与Long.Max_Value不对称的情况，
     * 但这里用int，所以应用内够用了
     */
    public static Date plusHours(Date date,int hours){
        if (date == null) {
            return null;
        }
        LocalDateTime dueDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        return Date.from(dueDateTime
                .plusHours(hours)
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }

    public static Date plusMins(Date date,int mins){
        if (date == null) {
            return null;
        }
        LocalDateTime dueDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        return Date.from(dueDateTime
                .plusMinutes(mins)
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }

    public static final String DATE_FORMAT_SEC = "yyyy-MM-dd HH:mm:ss";
    public static String date2Str(Date date) {
        if (null == date) return "";
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_SEC);
        return sdf.format(date);
    }

    public static String date2Str(Long date) {
        return date2Str(new Date(date));
    }

    public static String date2Str(Date date, String pattern) {
        if (null == date) return "";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    public static int getHour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    public static Date getDate(String dateStr) {
        DateFormat format = new SimpleDateFormat(DATE_FORMAT_SEC);
        try {
            return format.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Date timeStamp2Date(Long dateLong) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        Date date = new Date();
        date.setTime(dateLong);
        return dateFormat.parse(dateFormat.format(date));
    }
    public static Date truncate(Date date, int i) {
        return DateUtils.truncate(date, i);
    }

    public static String getZoneDate(Date date) {
        SimpleDateFormat sdf0=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf0.setTimeZone(TimeZone.getTimeZone("UTC+0"));
        return sdf0.format(date);
    }

}
