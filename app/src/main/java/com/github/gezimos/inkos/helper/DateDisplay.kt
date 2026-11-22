package com.github.gezimos.inkos.helper

import android.content.Context
import androidx.annotation.StringRes
import com.github.gezimos.inkos.R
import java.text.SimpleDateFormat
import java.util.Date

internal fun Context.formatDisplayDate(@StringRes pattern: Int, date: Date): String {
    return SimpleDateFormat(getString(pattern), resources.configuration.locales[0]).format(date)
}

internal fun Context.formatHomeDate(style: Int): String {
    val pattern = when (style) {
        1 -> R.string.date_pattern_home_month_first
        2 -> R.string.date_pattern_short
        3 -> R.string.date_pattern_home_day_first
        4 -> R.string.date_pattern_weekday
        else -> R.string.date_pattern_home_weekday_first
    }
    return formatDisplayDate(pattern, Date())
}
