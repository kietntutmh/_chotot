package com.vn.chotot.helper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
import java.util.TimeZone;

public class DateTime {
    /**
     * Get String value of Date time
     *
     * @param date
     * @param format - eg: yyyy/MM/dd HH:mm:ss
     * @return String value
     */
    public static String getDateInStringFormat(Date date, String format) {
        StringBuilder retBuf = new StringBuilder();
        if (date == null) {
            date = new Date();
        }
        DateFormat df = new SimpleDateFormat(format);
        // Set default timezone to UTC+7
        df.setTimeZone(TimeZone.getTimeZone(ZoneId.of("Asia/Ho_Chi_Minh")));
        retBuf.append(df.format(date));
        return retBuf.toString();
    }

    public static Date covertDateTimeStringToDate(String dateTime, String format) {
        Date date;
        try {
         date =  new SimpleDateFormat(format).parse(dateTime);
        } catch (ParseException e) {
            return null;
        }
        return date;
    }

    public static String subtractDateTimeToString(Date dateTime1, Date dateTime2, String format) {
        long result = dateTime1.getTime() - dateTime2.getTime();
        return getDateInStringFormat(new Date(result), format);
    }

    public static long subtractDateTime(Date dateTime1, Date dateTime2) {
        return dateTime1.getTime() - dateTime2.getTime();
    }

    public static String convertDeltaTimeToString(long deltaTime) {
        long seconds = deltaTime / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        return hours + ":" + minutes + ":" + seconds;
    }

    public static boolean checkDateValid(String dateToValidate, String dateFormat) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
            sdf.setLenient(false);
            sdf.parse(dateToValidate);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}
