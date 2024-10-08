package com.yuk.common.holiday.data

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.cfg.CoercionAction
import com.fasterxml.jackson.databind.cfg.CoercionInputShape
import com.fasterxml.jackson.databind.exc.MismatchedInputException
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.yuk.common.holiday.Holiday
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import java.time.Duration
import java.time.LocalDate
import java.time.Year
import java.time.YearMonth
import java.time.format.DateTimeFormatter

internal class GovernmentHolidayApiProvider(
    private val apiKey: String,
) : HolidayProvider {
    private val webClient =
        WebClient
            .builder()
            .baseUrl("http://apis.data.go.kr/B090041/openapi/service/SpcdeInfoService")
            .build()
    private val formatter = DateTimeFormatter.ofPattern("yyyyMMdd")
    private val objectMapper =
        ObjectMapper().apply {
            registerKotlinModule()
            findAndRegisterModules()
            coercionConfigFor(SingleItems::class.java).setCoercion(CoercionInputShape.EmptyString, CoercionAction.AsNull)
            coercionConfigFor(MultiItems::class.java).setCoercion(CoercionInputShape.EmptyString, CoercionAction.AsNull)
        }

    override fun getYearHolidays(year: Year): List<Holiday> {
        val spec =
            webClient.get().uri {
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

        val spec =
            webClient.get().uri {
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
        val raw =
            spec
                .retrieve()
                .bodyToMono<String>()
                .retry(3)
                .timeout(Duration.ofSeconds(3))
                .block()!!

        val data =
            try {
                objectMapper.readValue(raw)
            } catch (e: MismatchedInputException) {
                val single = objectMapper.readValue<GovernmentSingleHolidayResponse>(raw)

                val multiBody =
                    if (single.response.body?.items == null) {
                        null
                    } else {
                        MultiBody(
                            MultiItems(listOf(single.response.body.items.item)),
                            single.response.body.numOfRows,
                            single.response.body.pageNo,
                            single.response.body.totalCount,
                        )
                    }

                GovernmentMultiHolidayResponse(
                    MultiResponse(
                        multiBody,
                        single.response.header,
                    ),
                )
            }

        if (data.response.header.resultCode != "00") {
            throw RuntimeException("can't get GovernmentHoliday. errorCode: ${data.response.header.resultCode}")
        }

        val holidayList =
            data.response.body
                ?.items
                ?.item ?: listOf()

        return holidayList.map(::toHoliday)
    }

    private fun toHoliday(item: Item): Holiday {
        val localDate = LocalDate.parse(item.locdate.toString(), formatter)

        return Holiday(item.dateName, localDate)
    }
}
