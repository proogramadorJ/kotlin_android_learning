package com.pedrodev.inmemorytodolist.util

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object DateUtil {

    @RequiresApi(Build.VERSION_CODES.O)
    fun formatDate(date: LocalDate): String {
        var formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy")
        return formatter.format(date)
    }
}