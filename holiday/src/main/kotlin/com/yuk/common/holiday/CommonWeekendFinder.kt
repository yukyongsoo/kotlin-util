package com.yuk.common.holiday

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.temporal.ChronoField

internal class CommonWeekendFinder {
    fun getAllWeekEndOfMonth(yearMonth: YearMonth): MutableList<Holiday> {
        val list = mutableListOf<LocalDate>()

        var localDate = yearMonth.atDay(1)
        val lastLocalDate = yearMonth.atEndOfMonth()

        while (localDate <= lastLocalDate) {
            val day = DayOfWeek.of(localDate.get(ChronoField.DAY_OF_WEEK))

            if (day == DayOfWeek.SUNDAY || day == DayOfWeek.SATURDAY) {
                list.add(localDate)
            }

            localDate = localDate.plusDays(1)
        }

        return list.mapTo(mutableListOf()) {
            Holiday("WEEKEND", it)
        }
    }
}
