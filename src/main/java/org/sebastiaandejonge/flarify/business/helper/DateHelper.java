package org.sebastiaandejonge.flarify.business.helper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Date helper
 */
public class DateHelper {

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * Gets the fallback date which is the earliest date for which the API will return results. Yes, this could be
     * configurable via the application properties.
     *
     * @return A date object representing the fallback date
     */
    public static Date getFallbackDate() {
        return getDate(2010, Calendar.JANUARY, 1);
    }

    /**
     * Gets a plain date object for the given year, month and day.
     *
     * @param year The year
     * @param month The month (zero-based, use Calendar.MONTH_NAME for the right month numbers)
     * @param day The day of the month
     * @return A date object
     */
    private static Date getDate(int year, int month, int day) {
        return new GregorianCalendar(year, month, day).getTime();
    }

    /**
     * Formats a date to a standardized printable string
     *
     * @param date The date object
     * @return The formatted string
     */
    public static String getPrintableString(Date date) {

        return simpleDateFormat.format(date);
    }
}
