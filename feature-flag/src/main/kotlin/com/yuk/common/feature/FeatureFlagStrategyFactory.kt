package com.yuk.common.feature

import java.time.LocalDate
import java.time.LocalDateTime
import java.util.concurrent.ConcurrentHashMap

object FeatureFlagStrategyFactory {
    private val map = ConcurrentHashMap<String, FeatureFlagStrategy>()

    fun get(key: String): FeatureFlagStrategy = map[key] ?: throw IllegalStateException("can't find strategy $key")

    fun put(
        key: String,
        value: FeatureFlagStrategy,
    ) {
        map[key] = value
    }

    fun putBooleans(
        key: String,
        valueProvider: () -> Boolean,
    ) {
        val strategy = BooleanFlagStrategy(valueProvider)
        map[key] = strategy
    }

    fun putDateRange(
        key: String,
        fromValueProvider: () -> LocalDate,
        untilValueProvider: () -> LocalDate = { LocalDate.MAX },
    ) {
        val strategy =
            DateRangeFlagStrategy(fromValueProvider, untilValueProvider)
        map[key] = strategy
    }

    fun putDateTimeRange(
        key: String,
        fromValueProvider: () -> LocalDateTime,
        untilValueProvider: () -> LocalDateTime = { LocalDateTime.MAX },
    ) {
        val strategy =
            DateTimeRangeFlagStrategy(fromValueProvider, untilValueProvider)
        map[key] = strategy
    }
}
