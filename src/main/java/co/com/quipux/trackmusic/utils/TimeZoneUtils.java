/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.quipux.trackmusic.utils;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 *
 * @author mmarsiglia
 */
public class TimeZoneUtils {

    public static String getTimezoneOffset(TimeZone tz) {

       // TimeZone tz = TimeZone.getDefault();
        Calendar cal = GregorianCalendar.getInstance(tz);
        int offsetInMillis = tz.getOffset(cal.getTimeInMillis());

        String offset = String.format("%02d:%02d", Math.abs(offsetInMillis / 3600000), Math.abs((offsetInMillis / 60000) % 60));
        offset = (offsetInMillis >= 0 ? "+" : "-") + offset;               

        return offset;
    }
    
     public static String getTimezoneLocalset(TimeZone tz) {

       // TimeZone tz = TimeZone.getDefault();
        Calendar cal = GregorianCalendar.getInstance(tz);
        int offsetInMillis = tz.getOffset(cal.getTimeInMillis());

        String offset = String.format("%02d:%02d", Math.abs(offsetInMillis / 3600000), Math.abs((offsetInMillis / 60000) % 60));
        offset = (offsetInMillis >= 0 ? "-" : "+") + offset;               

        return offset;
    }
    
     public static int getTimezoneIntOffset(TimeZone tz) {
       // TimeZone tz = TimeZone.getDefault();
        Calendar cal = GregorianCalendar.getInstance(tz);
        int offsetInMillis = tz.getOffset(cal.getTimeInMillis());
        return offsetInMillis;
    }

}
