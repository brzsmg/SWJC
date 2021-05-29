package com.swlibs.common.types;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import com.swlibs.common.system.Log;

/**
 * Класс для работы с датой и временем.
 */
public class DateTime {
    //YYYY-MM-DD HH24:MI:SS
    final private static String pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    //final static String patternText = "dd.MM.yyyy HH:mm:ss";
    //final static String patternSql = "yyyy-MM-dd HH:mm:ss";

    final private static String patternFileName = "yyyyMMdd_HHmmss";

    private Date mDate;
    private static SimpleDateFormat sFormatUTC = null;
    //private SimpleDateFormat mFormatSql;

    public static long getCurrentTime() {
        return System.currentTimeMillis();
    }

    public static String getPatternFileName() {
        return patternFileName;
    }

    private void initFormat() {
        if(sFormatUTC == null) {
            sFormatUTC = new SimpleDateFormat(pattern, Locale.getDefault());
            sFormatUTC.setTimeZone(TimeZone.getTimeZone("GMT"));
            /*mFormatSql = new SimpleDateFormat(patternSql);
            mFormatSql.setTimeZone(TimeZone.getTimeZone("Asia/Yekaterinburg"));*/
        }
    }

    public static Date now() {
        return new Date();
    }

    public static Date fromLong(long timestamp) {
        return new Date(timestamp);
    }

    public DateTime() {
        initFormat();
        mDate = new Date();
    }

    public DateTime(long timestamp) {
        initFormat();
        mDate = new Date(timestamp);
    }

    public DateTime(String date) {
        initFormat();
        try {
            mDate = sFormatUTC.parse(date);
        } catch (ParseException e) {
            Log.e("parse", e);
            mDate = new Date();
        }
    }

    public DateTime(Date date) {
        initFormat();
        mDate = (Date)date.clone();
    }

    public DateTime fromDate(Date date) {
        if(date == null) {
            return null;
        } else {
            return new DateTime(date);
        }
    }

    public static String toDiffString(Date date, String isnull) {
        if(date == null) {
            return isnull;
        } else {
            DateTime dt = new DateTime(date);
            if (dt.isToday()) {
                return dt.toFormat("HH:mm");
            } else {
                return dt.toFormat("dd.MM.yyyy HH:mm");
            }
        }
    }

    public static Long toDiffDates(Date date_start, Date date_end) {
        if(date_start == null) {
            return null;
        }
        if(date_end == null) {
            return null;
        }
        return date_end.getTime() - date_start.getTime();
    }

    //TODO: isToday для какой timeZone?
    public Boolean isToday() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        Date today = c.getTime();
        long todayInMillis = c.getTimeInMillis();

        Calendar c2 = Calendar.getInstance();
        c2.setTime(mDate);
        c2.set(Calendar.HOUR_OF_DAY, 0);
        c2.set(Calendar.MINUTE, 0);
        c2.set(Calendar.SECOND, 0);
        c2.set(Calendar.MILLISECOND, 0);
        long inMillis = c2.getTimeInMillis();

        return todayInMillis == inMillis;
    }

    /*public DateTime(String date, Boolean utc){
        initFormat();
        try {
            if(utc){//UTC
                mDate = mFormatUTC.parse(date);
            }else{//Yekaterinburg
                mDate = mFormatSql.parse(date);
            }
        } catch (ParseException e) {
            Log.e("parse", e);
            mDate = new Date();
        }
    }*/

    public Date getSystemType() {
        return mDate;
    }

    public long getTime() {
        return mDate.getTime();
    }

    public Date toDate() {
        return (Date)mDate.clone();
    }

    public String toString() {
        //return String.format("%1$tY-%1$tm-%1$tdT%1$tT", mDate);
        return sFormatUTC.format(mDate);
    }

    public Long getDifference(DateTime date) {
        return date.getTime() - this.mDate.getTime();
    }

    public static Long getDifference(Date firstDate, Date secondDate) {
        return firstDate.getTime() - secondDate.getTime();
    }

    public String toFormat(String format) {
        return new SimpleDateFormat(format, Locale.getDefault()).format(mDate);
    }

    public static String toFormat(Date date, String format) {
        return new SimpleDateFormat(format, Locale.getDefault()).format(date);
    }

    public static Date parseDate(String date) {
        if(date == null) {
            return null;
        }
        try {
            return sFormatUTC.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}
