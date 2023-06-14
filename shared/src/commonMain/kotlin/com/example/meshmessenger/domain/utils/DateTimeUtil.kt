package com.example.meshmessenger.domain.utils

import kotlinx.datetime.*

object DateTimeUtil {

    fun now(): LocalDateTime {
        return Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
    }

    fun toEpochMillis(dateTime: LocalDateTime): Long {
        return dateTime.toInstant(TimeZone.currentSystemDefault()).toEpochMilliseconds()
    }

    fun testTime() {
        val timeNow = now()
        val timeLong = toEpochMillis(timeNow)
        val timeLongInstant = Instant.fromEpochMilliseconds(timeLong)
        val timeFormat = formatDate(timeLongInstant.toLocalDateTime(TimeZone.UTC))
        println("--------------------------time now $timeNow")
        println("--------------------------time long $timeLong")
        println("--------------------------time longInstant $timeLongInstant")
        println("--------------------------time format $timeFormat")
    }

    fun formatDate(dateTime: LocalDateTime): String {
        val month = dateTime.month.name.lowercase().take(3).replaceFirstChar { it.uppercase() }
        val day = if(dateTime.dayOfMonth < 10) "0${dateTime.dayOfMonth}" else dateTime.dayOfMonth
        val year = dateTime.year
        val hour = if(dateTime.hour < 10) "0${dateTime.hour}" else dateTime.hour
        val minute = if(dateTime.minute < 10) "0${dateTime.minute}" else dateTime.minute

        return buildString {
            append(month)
            append(" ")
            append(day)
            append(" ")
            append(year)
            append(", ")
            append(hour)
            append(":")
            append(minute)
        }
    }
}