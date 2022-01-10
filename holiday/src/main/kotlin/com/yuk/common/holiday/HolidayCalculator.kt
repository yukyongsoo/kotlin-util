package com.yuk.common.holiday

import com.yuk.common.holiday.cache.CacheProvider
import com.yuk.common.holiday.cache.MemoryCacheProvider
import com.yuk.common.holiday.data.GovernmentHolidayApiProvider
import com.yuk.common.holiday.data.HolidayProvider
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Period
import java.time.YearMonth

class HolidayCalculator(
    private val holidayProvider: HolidayProvider,
    private val cacheProvider: CacheProvider? = MemoryCacheProvider()
) {
    constructor(apiKey: String, useCache: Boolean = true) : this(
        GovernmentHolidayApiProvider(apiKey),
        if (useCache) MemoryCacheProvider() else null
    )

    private val commonWeekendFinder = CommonWeekendFinder()

    fun isBusinessDay(dateTime: LocalDateTime): Boolean {
        return isBusinessDay(dateTime.toLocalDate())
    }

    fun isBusinessDay(date: LocalDate): Boolean {
        return findHoliday(date) == null
    }

    fun isHoliday(dateTime: LocalDateTime): Boolean {
        return isHoliday(dateTime.toLocalDate())
    }

    fun isHoliday(date: LocalDate): Boolean {
        return findHoliday(date) != null
    }

    fun calculateAfterBusinessDay(
        startDate: LocalDateTime,
        endDate: LocalDateTime = LocalDateTime.now(),
        includeStartDay: Boolean = false
    ): Int {
        return calculateAfterBusinessDay(
            startDate.toLocalDate(), endDate.toLocalDate(), includeStartDay
        )
    }

    fun calculateAfterBusinessDay(
        startDate: LocalDate,
        endDate: LocalDate = LocalDate.now(),
        includeStartDay: Boolean = false
    ): Int {
        val nextBusinessDay = getBusinessDay(startDate)

        var start = if (nextBusinessDay == startDate) startDate else nextBusinessDay
        start = if (includeStartDay.not())
            start.plusDays(1)
        else
            start

        val period = Period.between(start, endDate)

        val holidays = findHolidayInRange(start, endDate)

        val day = period.days - holidays.size

        return day + 1
    }

    fun calculateListAfterBusinessDay(
        sourceDates: Collection<LocalDateTime>,
        targetDate: LocalDateTime = LocalDateTime.now(),
        includeStartDay: Boolean = false
    ): List<Int> {
        return calculateListAfterBusinessDay(
            sourceDates.map { it.toLocalDate() },
            targetDate.toLocalDate(),
            includeStartDay
        )
    }

    fun calculateListAfterBusinessDay(
        startDates: Collection<LocalDate>,
        endDate: LocalDate = LocalDate.now(),
        includeStartDay: Boolean = false
    ): List<Int> {
        val localCache = mutableMapOf<Pair<LocalDate, LocalDate>, Int>()

        return startDates.map {
            val day = localCache[it to endDate]

            if (day == null) {
                localCache[it to endDate] =
                    calculateAfterBusinessDay(it, endDate, includeStartDay)
            }

            localCache[it to endDate]!!
        }
    }

    fun getDateAfterBusinessDay(
        localDateTime: LocalDateTime,
        day: Int,
        includeStartDay: Boolean = false
    ): LocalDateTime {
        val date = getDateAfterBusinessDay(localDateTime.toLocalDate(), day, includeStartDay)

        return LocalDateTime.of(date, localDateTime.toLocalTime())
    }

    fun getDateAfterBusinessDay(
        localDate: LocalDate,
        day: Int,
        includeStartDay: Boolean = false
    ): LocalDate {
        val nextBusinessDay = getBusinessDay(localDate)

        val start = if (nextBusinessDay == localDate) localDate else nextBusinessDay

        val addedDay = if (includeStartDay.not())
            day
        else
            day - 1

        val date = start.plusDays(addedDay.toLong())

        val holidays = findHolidayInRange(start, date)

        return date.plusDays(holidays.size.toLong() + 1)
    }

    fun getBusinessDay(localDate: LocalDate): LocalDate {
        val yearMonth = YearMonth.from(localDate)

        return if (isBusinessDay(localDate)) localDate
        else {
            val holidayList = findAndCaching(yearMonth).map { it.localDate }

            var businessDay = localDate

            while (businessDay in holidayList) {
                businessDay = businessDay.plusDays(1)
            }

            businessDay
        }
    }

    private fun findHolidayInRange(
        startDate: LocalDate,
        endDate: LocalDate
    ): List<Holiday> {
        val startYearMonth = YearMonth.from(startDate)
        val endYearMonth = YearMonth.from(endDate)

        val startDateHolidays =
            cacheProvider?.getHolidayList(startYearMonth) ?: findAndCaching(
                startYearMonth
            )
        val endDateHolidays =
            cacheProvider?.getHolidayList(endYearMonth) ?: findAndCaching(
                endYearMonth
            )

        val set = startDateHolidays.toMutableSet()
        set.addAll(endDateHolidays)

        return set.filter { it.localDate in startDate..endDate }
    }

    private fun findHoliday(date: LocalDate): Holiday? {
        val yearMonth = YearMonth.from(date)

        val monthHolidays = cacheProvider?.getHolidayList(yearMonth)
            ?: findAndCaching(yearMonth)

        return monthHolidays.firstOrNull { it.localDate == date }
    }

    private fun findAndCaching(yearMonth: YearMonth): List<Holiday> {
        val allList = commonWeekendFinder.getAllWeekEndOfMonth(yearMonth)

        val list = holidayProvider.getYearMonthHolidays(yearMonth)

        allList.addAll(list)

        cacheProvider?.addHolidays(yearMonth, allList)

        return allList
    }
}
