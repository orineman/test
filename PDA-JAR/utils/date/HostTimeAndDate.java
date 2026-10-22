package com.infowave.pda.utils.date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import org.apache.commons.lang.time.DateUtils;

/**
 * Created by IntelliJ IDEA.
 * User: Alexey.Ogarkov
 * Date: 25.09.2007
 * Time: 14:07:59
 * To change this template use File | Settings | File Templates.
 */
public class HostTimeAndDate {

    public static final long EPOCH_DIFF = 11644473600000L;

    public static Date filetimeToOffsetDate(final long filetime, long timezoneOffset,Locale locale) {
        GregorianCalendar calendar = new GregorianCalendar(locale);
        calendar.clear();
        TimeZone timeZone = TimeZone.getTimeZone("GMT");
        //calendar.setTime(new Date((filetime / 10000) - EPOCH_DIFF));
        timeZone.setRawOffset((int) (timezoneOffset * DateUtils.MILLIS_PER_MINUTE));
        calendar.setTimeZone(timeZone);
        return calendar.getTime();
    }

    public static Date filetimeToDate(final long filetime) {
        return new Date((filetime / 10000) - EPOCH_DIFF);
    }
    
    public static Date filetimeToDate(final int high, final int low) {
        final long filetime = ((long) high) << 32 | (low & 0xffffffffL);
        final long ms_since_16010101 = filetime / (1000 * 10);
        final long ms_since_19700101 = ms_since_16010101 - EPOCH_DIFF;
        return new Date(ms_since_19700101);
    }        

    public static String filetimeToString(String longS) {
        try {
            return filetimeToDate(Long.parseLong(longS)).toString();
        } catch (NumberFormatException e) {
            return "";
        }
    }

    public static String filetimeToFormattedDate(String longs,int style) {
        try {
            return DateFormat.getDateInstance(style).format(filetimeToDate(Long.parseLong(longs)));
        } catch (NumberFormatException e) {
            return "";
        }
    }

    public static String filetimeToFormattedDate(String longs,int style, String l) {
        try {
            return DateFormat.getDateInstance(style,new Locale(l)).format(filetimeToDate(Long.parseLong(longs)));
        } catch (NumberFormatException e) {
            return "";
        }
    }

    public static String filetimeToFormattedTimeDate(String longs,int tstyle,int dstyle) {
        try {
            return DateFormat.getDateTimeInstance(tstyle,dstyle).format(filetimeToDate(Long.parseLong(longs)));
        } catch (NumberFormatException e) {
            return "";
        }
    }

    public static String filetimeToFormattedTimeDate(String longs,int tstyle,int dstyle, String l) {
        try {
            return DateFormat.getDateTimeInstance(tstyle,dstyle,new Locale(l)).format(filetimeToDate(Long.parseLong(longs)));
        } catch (NumberFormatException e) {
            return "";
        }
    }

    public static String filetimeToOffsetFormattedTimeDate(String longs,int tstyle, int dstyle,String l,int timezoneOffset) {
        try {
            return DateFormat.getDateTimeInstance(tstyle,dstyle,new Locale(l)).format(filetimeToOffsetDate(Long.parseLong(longs),timezoneOffset,new Locale(l)));
        } catch (NumberFormatException e) {
            return "";
        }
    }

    public static Date filetimeToDate(String longS) throws NumberFormatException{
            if (!longS.trim().equals("")) {
                return filetimeToDate(Long.parseLong(longS));
            } else {
                return null;
            }
    }

     public static long dateToFileTime(final Date date)
     {
         long ms_since_19700101 = date.getTime();
         long ms_since_16010101 = ms_since_19700101 + EPOCH_DIFF;
         return ms_since_16010101 * (1000 * 10);
     }

    public static String getDayFromFileTime ( final String filetime )
    {
        GregorianCalendar calendar = getCalendarFromFileTime( filetime );
        return Integer.toString( calendar.get( Calendar.DAY_OF_MONTH ), 10 );
    }

    public static String getMonthFromFileTime ( final String filetime )
    {
        GregorianCalendar calendar = getCalendarFromFileTime( filetime );
        return Integer.toString( calendar.get( Calendar.MONTH ) + 1, 10 );
    }

    public static String getYearFromFileTime ( final String filetime )
    {
        GregorianCalendar calendar = getCalendarFromFileTime( filetime );
        return Integer.toString( calendar.get( Calendar.YEAR ), 10 );
    }

    public static String getHourFromFileTime ( final String filetime )
    {
        GregorianCalendar calendar = getCalendarFromFileTime( filetime );
        return Integer.toString( calendar.get( Calendar.HOUR_OF_DAY ), 10 );
    }

    public static String getMinuteFromFileTime ( final String filetime )
    {
        GregorianCalendar calendar = getCalendarFromFileTime( filetime );
        return Integer.toString( calendar.get( Calendar.MINUTE ), 10 );
    }

    private static Log m_log = LogFactory.getLog(HostTimeAndDate.class);

    public static Long getLocaleDateForDMYHM(final String day, final String month, final String year ,final String hour,final String minute, Locale locale,long timezoneOffset) {
        int iDay = Integer.parseInt( day );
        int iMonth = Integer.parseInt( month );
        int iYear = Integer.parseInt( year );
        int iHour = Integer.parseInt( hour );
        int iMinute = Integer.parseInt( minute );

        GregorianCalendar calendar = new GregorianCalendar(locale);
        calendar.clear();
        TimeZone timeZone = TimeZone.getTimeZone("GMT");
        timeZone.setRawOffset((int) (timezoneOffset * DateUtils.MILLIS_PER_MINUTE));
        calendar.setTimeZone(timeZone);
        calendar.set( iYear, iMonth-1, iDay, iHour, iMinute );
        if (calendar.get(Calendar.MONTH)!=iMonth-1) {
            if (calendar.get(Calendar.MONTH)>iMonth-1) {
                calendar.set(Calendar.MONTH, iMonth-1);
                calendar.set (iYear,iMonth-1, calendar.getActualMaximum(Calendar.DAY_OF_MONTH), iHour, iMinute);
            }
        }
        return Long.valueOf( dateToFileTime(calendar.getTime()));
    }
    
    public static Long getLocaleDateForDMY ( final String day, final String month, final String year ,Locale locale,long timezoneOffset) {
        int iDay = Integer.parseInt( day );
        int iMonth = Integer.parseInt( month );
        int iYear = Integer.parseInt( year );

        GregorianCalendar calendar = new GregorianCalendar(locale);
        calendar.clear();
        
        return fillCalendar(iDay,iMonth,iYear,timezoneOffset,calendar);
    }

    public static Long getDateForDMY ( final String day, final String month, final String year )
    {
        int iDay = Integer.parseInt( day );
        int iMonth = Integer.parseInt( month );
        int iYear = Integer.parseInt( year );

        GregorianCalendar calendar = new GregorianCalendar();
        calendar.clear();
        
        return fillCalendar(iDay,iMonth,iYear,0,calendar);
    }
    
    protected static Long fillCalendar( int day, int month, int year, long timezoneOffset , GregorianCalendar calendar) {
        TimeZone timeZone = TimeZone.getTimeZone("GMT");
        timeZone.setRawOffset((int) (timezoneOffset * DateUtils.MILLIS_PER_MINUTE));
        calendar.setTimeZone(timeZone);
        calendar.set( year, month-1, day );
        //If we set day for the next month test if month is changed
        if (calendar.get(Calendar.MONTH)!=month-1) {
            if (calendar.get(Calendar.MONTH)>month-1) {
                calendar.set(Calendar.MONTH, month-1);
                calendar.set (year,month-1, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            }
        }
        
        return Long.valueOf( dateToFileTime(calendar.getTime()));    
    }

    public static Long getDateForDMYWithNoCheck (final String day, final String month, final String year) {
        int iDay = Integer.parseInt( day );
        int iMonth = Integer.parseInt( month );
        int iYear = Integer.parseInt( year );

        GregorianCalendar calendar = new GregorianCalendar();
        calendar.clear();
        calendar.set( iYear, iMonth-1, iDay );
        //If we set day for the next month test if month is changed

        return Long.valueOf( dateToFileTime( calendar.getTime() ) );
    }

    public static Long getDateForDMYHM ( final String day, final String month, final String year, final String hour, final String minute )
    {
        int iDay = Integer.parseInt( day );
        int iMonth = Integer.parseInt( month );
        int iYear = Integer.parseInt( year );
        int iHour = Integer.parseInt( hour );
        int iMinute = Integer.parseInt( minute );

        GregorianCalendar calendar = new GregorianCalendar();
        calendar.clear();
        calendar.set( iYear, iMonth-1, iDay, iHour, iMinute );
        if (calendar.get(Calendar.MONTH)!=iMonth-1) {
            if (calendar.get(Calendar.MONTH)>iMonth-1) {
                calendar.set(Calendar.MONTH, iMonth-1);
                calendar.set (iYear,iMonth-1, calendar.getActualMaximum(Calendar.DAY_OF_MONTH), iHour, iMinute);
            }
        }
        return Long.valueOf( dateToFileTime( calendar.getTime() ) );
    }

    protected static GregorianCalendar getCalendarFromFileTime ( final String filetime )
    {
        long intDate = Long.parseLong( filetime, 10 );
        Date dtDate  = filetimeToDate( intDate );
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime( dtDate );
        return calendar;
    }
}
