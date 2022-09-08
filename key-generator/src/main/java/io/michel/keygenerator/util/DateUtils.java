package io.michel.keygenerator.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    public static Date today() {
        return new Date();
    }

    public static String todayStr() {
        return sdf.format(today());
    }

    public static String formattedDate(Date date) {
        return date != null ? sdf.format(date) : todayStr();
    }

    public static Date addYears(Date date, int amount) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.YEAR, amount);
        return c.getTime();
    }

}