package com.lkby.common.extensions

import java.util.Calendar
import java.util.TimeZone

/**
 * Converts a local timestamp to UTC midnight of the same visual date.
 * Necessary because Material 3 DatePicker works in UTC.
 */
fun Long.toUtcMidnight(): Long {
    val localCalendar = Calendar.getInstance()
    localCalendar.timeInMillis = this
    
    val utcCalendar = Calendar.getInstance(TimeZone.getTimeZone("UTC")).apply {
        clear()
        set(
            localCalendar.get(Calendar.YEAR),
            localCalendar.get(Calendar.MONTH),
            localCalendar.get(Calendar.DAY_OF_MONTH)
        )
    }
    return utcCalendar.timeInMillis
}

/**
 * Returns UTC midnight for "today" in the user's local timezone.
 */
fun getLocalTodayUtcMidnight(): Long {
    val localCalendar = Calendar.getInstance()
    val utcCalendar = Calendar.getInstance(TimeZone.getTimeZone("UTC")).apply {
        clear()
        set(
            localCalendar.get(Calendar.YEAR),
            localCalendar.get(Calendar.MONTH),
            localCalendar.get(Calendar.DAY_OF_MONTH)
        )
    }
    return utcCalendar.timeInMillis
}