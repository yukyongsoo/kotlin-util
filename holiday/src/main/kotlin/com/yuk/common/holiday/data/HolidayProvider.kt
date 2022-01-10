package com.yuk.common.holiday.data

import com.yuk.common.holiday.Holiday
import java.time.Year
import java.time.YearMonth

interface HolidayProvider {
    fun getYearHolidays(year: Year): List<Holiday>
    fun getYearMonthHolidays(yearMonth: YearMonth): List<Holiday>
}
