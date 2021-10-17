package com.iram.playeventvideos.utils

import java.text.SimpleDateFormat
import java.util.*


class DateFormat {
    companion object {
        private val yyyy_MM_dd_HH_mm: SimpleDateFormat = SimpleDateFormat(
            "yyyy-MM-dd HH:mm", Locale.getDefault()
        )
        private val HHmm: SimpleDateFormat = SimpleDateFormat(
            "HH:mm",
            Locale.getDefault()
        )
        private val MM_dd_HHmm: SimpleDateFormat = SimpleDateFormat(
            "MM-dd HH:mm", Locale.getDefault()
        )

        fun stringToDate(stringDate: String): Date? {
            val formatter = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSSSSS'Z'", Locale.getDefault())
            return formatter.parse(stringDate)
        }
        fun dateToDayTime(oldTime: Date?): String? {
            val newTime = Date()
            try {
                val cal: Calendar = Calendar.getInstance()
                cal.time = newTime
                val oldCal: Calendar = Calendar.getInstance()
                oldCal.time = oldTime
                val oldYear: Int = oldCal.get(Calendar.YEAR)
                val year: Int = cal.get(Calendar.YEAR)
                val oldDay: Int = oldCal.get(Calendar.DAY_OF_YEAR)
                val day: Int = cal.get(Calendar.DAY_OF_YEAR)
                if (oldYear == year) {
                    val value = oldDay - day
                    return if (value == -1) {
                        "Yesterday, " + HHmm.format(oldTime)
                    } else if (value == 0) {
                        "Today, " + HHmm.format(oldTime)
                    } else if (value == 1) {
                        "Tomorrow, " + HHmm.format(oldTime)
                    } else {
                        MM_dd_HHmm.format(oldTime)
                    }
                }
            } catch (e: Exception) {
            }
            return yyyy_MM_dd_HH_mm.format(oldTime)
        }
    }
}