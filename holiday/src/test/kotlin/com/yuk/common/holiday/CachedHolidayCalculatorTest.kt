package com.yuk.common.holiday

import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class CachedHolidayCalculatorTest {
    private val cachedCalc =
        HolidayCalculator("pOUq5oFGJa2fDyjizOSPbF3QZw6jPVaGPDoWFCD7PtPE/wdTIDFdw8iAFS6qqCp/g3X75iSZavzj6n68wV1ZcA==")

    @Test
    fun `영업일인지 확인하기`() {
        val date = LocalDate.of(2022, 1, 9)
        val time = LocalTime.now()

        val localDateTime = LocalDateTime.of(date, time)

        val result = cachedCalc.isBusinessDay(localDateTime)

        assert(result.not())

        val cachedResult = cachedCalc.isBusinessDay(localDateTime)

        assert(cachedResult.not())
    }

    @Test
    fun `비 영업일인지 확인하기`() {
        val date = LocalDate.of(2022, 1, 9)
        val time = LocalTime.now()

        val localDateTime = LocalDateTime.of(date, time)

        val result = cachedCalc.isHoliday(localDateTime)

        assert(result)

        val cachedResult = cachedCalc.isBusinessDay(localDateTime)

        assert(cachedResult.not())
    }

    @Test
    fun `오늘 또는 휴일 이 후 돌아오는 영업일 찾기`() {
        val date = LocalDate.of(2022, 1, 2)

        val next = cachedCalc.nextBusinessDay(date)

        assert(next == LocalDate.of(2022, 1, 3))
    }

    @Test
    fun `영업일 당일포함 영업일 기준으로 몇일이나 지났는지 확인하기`() {
        val start = LocalDateTime.of(2022, 1, 3, 0, 0, 0)
        val end = LocalDateTime.of(2022, 1, 9, 0, 0, 0)

        val day = cachedCalc.calculateAfterBusinessDay(start, end, true)

        assert(day == 5)
    }

    @Test
    fun `영업일 당일 비포함 영업일 기준으로 몇일이나 지났는지 확인하기`() {
        val start = LocalDateTime.of(2022, 1, 3, 0, 0, 0)
        val end = LocalDateTime.of(2022, 1, 9, 0, 0, 0)

        val day = cachedCalc.calculateAfterBusinessDay(start, end)

        assert(day == 4)
    }

    @Test
    fun `영업일 당일 포함 특정 영업일수가 지난 날짜 구하기`() {
        val start = LocalDateTime.of(2022, 1, 3, 0, 0, 0)

        val dateTime = cachedCalc.getDateAfterBusinessDay(start, 5, true)

        val targetDate = LocalDateTime.of(2022, 1, 8, 0, 0, 0)

        assert(targetDate == dateTime)
    }

    @Test
    fun `영업일 당일 비포함 특정 영업일수가 지난 날짜 구하기`() {
        val start = LocalDateTime.of(2022, 1, 3, 0, 0, 0)

        val dateTime = cachedCalc.getDateAfterBusinessDay(start, 4)

        val targetDate = LocalDateTime.of(2022, 1, 8, 0, 0, 0)

        assert(targetDate == dateTime)
    }

    @Test
    fun `비 영업일 당일포함 영업일 기준으로 몇일이나 지났는지 확인하기`() {
        val start = LocalDateTime.of(2022, 1, 1, 0, 0, 0)
        val end = LocalDateTime.of(2022, 1, 9, 0, 0, 0)

        val day = cachedCalc.calculateAfterBusinessDay(start, end, true)

        assert(day == 5)
    }

    @Test
    fun `비 영업일 당일 비포함 영업일 기준으로 몇일이나 지났는지 확인하기`() {
        val start = LocalDateTime.of(2022, 1, 1, 0, 0, 0)
        val end = LocalDateTime.of(2022, 1, 9, 0, 0, 0)

        val day = cachedCalc.calculateAfterBusinessDay(start, end)

        assert(day == 5)
    }

    @Test
    fun `비 영업일 당일 포함 특정 영업일수가 지난 날짜 구하기`() {
        val start = LocalDateTime.of(2022, 1, 1, 0, 0, 0)

        val dateTime = cachedCalc.getDateAfterBusinessDay(start, 5, true)

        val targetDate = LocalDateTime.of(2022, 1, 8, 0, 0, 0)

        assert(targetDate == dateTime)
    }

    @Test
    fun `비 영업일 당일 비포함 특정 영업일수가 지난 날짜 구하기`() {
        val start = LocalDateTime.of(2022, 1, 1, 0, 0, 0)

        val dateTime = cachedCalc.getDateAfterBusinessDay(start, 5)

        val targetDate = LocalDateTime.of(2022, 1, 8, 0, 0, 0)

        assert(targetDate == dateTime)
    }

    @Test
    fun `영업일 당일 제외 테스트 영업일 5일`() {
        val start = LocalDateTime.of(2022, 1, 17, 17, 15, 0)
        val dateTime = cachedCalc.getDateAfterBusinessDay(start, 5, false)

        val targetDate = LocalDateTime.of(2022, 1, 25, 17, 15, 0)

        assert(targetDate == dateTime)
    }

    @Test
    fun `영업일 당일 제외 테스트 영업일 6일`() {
        val start = LocalDateTime.of(2022, 1, 17, 17, 15, 0)
        val dateTime = cachedCalc.getDateAfterBusinessDay(start, 6, false)

        val targetDate = LocalDateTime.of(2022, 1, 26, 17, 15, 0)

        assert(targetDate == dateTime)
    }

    @Test
    fun `명절이 끼어있는 영업일 당일 제외 테스트 영업일 5일`() {
        val start = LocalDateTime.of(2022, 1, 26, 17, 15, 0)

        val dateTime = cachedCalc.getDateAfterBusinessDay(start, 5, false)

        val targetDate = LocalDateTime.of(2022, 2, 8, 17, 15, 0)

        assert(targetDate == dateTime)
    }
}
