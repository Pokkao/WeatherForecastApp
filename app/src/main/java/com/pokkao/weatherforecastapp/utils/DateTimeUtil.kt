package com.pokkao.weatherforecastapp.utils

import android.text.format.DateFormat
import com.pokkao.weatherforecastapp.R
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

const val DATE_TIME_PATTERN = "dd-MM-yyyy HH:mm"
const val DATE_PATTERN = "dd-MM-yyyy"
const val TIME_PATTERN = "hh:mm"
const val TIME_AM_PM_PATTERN = "hh:mm a"


fun convertTimestampToDate(
    time: Long,
    datePattern: String? = DATE_PATTERN
): String {
    val cal = Calendar.getInstance(Locale.US)
    cal.timeInMillis = time * 1000
    cal.add(Calendar.HOUR_OF_DAY, -7)
    return DateFormat.format(datePattern, cal).toString()
}


fun convert24HourTo12Hour(time: String): String {
    val tk = StringTokenizer(time)
    val date = tk.nextToken()
    val time = tk.nextToken()

    val sdFormatTime = SimpleDateFormat(TIME_PATTERN)
    val sdFormatTimeAMPM = SimpleDateFormat(TIME_AM_PM_PATTERN)

    val dt: Date
    return try {
        dt = sdFormatTime.parse(time)
        sdFormatTimeAMPM.format(dt)

        if (sdFormatTimeAMPM.format(dt).contains(Contextor.context.getString(R.string.time_pm))
        ) {
            sdFormatTimeAMPM.format(dt).replace(
                Contextor.context.getString(R.string.time_pm),
                Contextor.context.getString(R.string.pm)
            )
        } else {
            sdFormatTimeAMPM.format(dt)
                .replace(
                    Contextor.context.getString(R.string.time_am),
                    Contextor.context.getString(R.string.am)
                )
        }

    } catch (e: ParseException) {
        ""
    }

}
