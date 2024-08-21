package com.yuk.common.holiday.cache

import com.yuk.common.holiday.Holiday
import java.time.YearMonth
import java.util.concurrent.ConcurrentHashMap

internal class MemoryCacheProvider : CacheProvider {
    private val cache = ConcurrentHashMap<YearMonth, MutableList<Holiday>>()

    override fun addHoliday(
        yearMonth: YearMonth,
        holiday: Holiday,
    ) {
        val list = cache[yearMonth]

        if (list == null) {
            cache[yearMonth] = mutableListOf(holiday)
        } else {
            list.add(holiday)
        }
    }

    override fun getHolidayList(yearMonth: YearMonth): List<Holiday>? = cache[yearMonth]

    override fun addHolidays(
        yearMonth: YearMonth,
        holidays: List<Holiday>,
    ) {
        val list = cache[yearMonth]

        if (list == null) {
            cache[yearMonth] = holidays.toMutableList()
        } else {
            list.addAll(holidays)
        }
    }
}
