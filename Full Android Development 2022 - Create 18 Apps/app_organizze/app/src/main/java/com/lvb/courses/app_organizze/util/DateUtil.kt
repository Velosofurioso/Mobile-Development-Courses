package com.lvb.courses.app_organizze.util

import java.text.SimpleDateFormat
import java.util.Locale

class DateUtil {
    companion object {

        fun actualDate() : String {
            val date = System.currentTimeMillis()
            return SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(date)

        }

        fun monthYearChosen(date: String) : String {

            val returnedDate = date.split("/")
            return returnedDate[1] + returnedDate[2]
        }
    }
}