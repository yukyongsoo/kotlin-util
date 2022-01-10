package com.yuk.common.holiday.cache

import com.yuk.common.holiday.Holiday
import java.time.YearMonth

interface CacheProvider {
    fun addHoliday(yearMonth: YearMonth, holiday: Holiday)
    fun getHolidayList(yearMonth: YearMonth): List<Holiday>?
    fun addHolidays(yearMonth: YearMonth, holidays: List<Holiday>)
}
