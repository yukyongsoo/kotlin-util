package com.yuk.common.holiday.data

import com.yuk.common.holiday.Holiday
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import java.time.LocalDate
import java.time.Year
import java.time.YearMonth
import java.time.format.DateTimeFormatter

internal class GovernmentHolidayApiProvider(
    private val apiKey: String
) : HolidayProvider {
    private val webClient = WebClient.builder()
        .baseUrl("http://apis.data.go.kr/B090041/openapi/service/SpcdeInfoService")
        .build()
    private val formatter = DateTimeFormatter.ofPattern("yyyyMMdd")

    override fun getYearHolidays(year: Year): List<Holiday> {
        val spec = webClient.get().uri {
            it.path("/getRestDeInfo")
            it.queryParam("serviceKey", apiKey)
            it.queryParam("solYear", year.value)
            it.queryParam("numOfRows", "200")
            it.queryParam("_type", "json")
            it.build()
        }

        return send(spec)
    }

    override fun getYearMonthHolidays(yearMonth: YearMonth): List<Holiday> {
        val month = String.format("%02d", yearMonth.monthValue)

        val spec = webClient.get().uri {
            it.path("/getRestDeInfo")
            it.queryParam("serviceKey", apiKey)
            it.queryParam("solYear", yearMonth.year)
            it.queryParam("solMonth", month)
            it.queryParam("numOfRows", "200")
            it.queryParam("_type", "json")
            it.build()
        }

        return send(spec)
    }

    private fun send(spec: WebClient.RequestHeadersSpec<*>): List<Holiday> {
        val data = spec.retrieve()
            .bodyToMono<GovernmentHolidayResponse>().block()!!

        if (data.response.header.resultCode != "00") throw RuntimeException("can't get GovernmentHoliday. errorCode: ${data.response.header.resultCode}")

        val holidayList = data.response.body!!.items.item

        return holidayList.map(::toHoliday)
    }

    private fun toHoliday(item: Item): Holiday {
        val localDate = LocalDate.parse(item.locdate.toString(), formatter)

        return Holiday(item.dateName, localDate)
    }
}
