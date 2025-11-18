package com.yuk.common.feature

import java.time.LocalDate
import java.time.LocalDateTime

interface FeatureFlagStrategy {
    fun isEnabled(): Boolean
}

class BooleanFlagStrategy private constructor(
    private val flag: ReferencedDataType<Boolean>,
) : FeatureFlagStrategy {
    constructor(valueProvider: () -> Boolean) : this(ReferencedDataType(valueProvider))

    override fun isEnabled(): Boolean = flag.value
}

class DateTimeRangeFlagStrategy private constructor(
    private val from: ReferencedDataType<LocalDateTime>,
    private val until: ReferencedDataType<LocalDateTime> = ReferencedDataType { LocalDateTime.MAX },
) : FeatureFlagStrategy {
    constructor(fromValueProvider: () -> LocalDateTime, untilValueProvider: () -> LocalDateTime) :
        this(ReferencedDataType(fromValueProvider), ReferencedDataType(untilValueProvider))

    override fun isEnabled(): Boolean {
        val current = LocalDateTime.now()

        return current in from.value..<until.value
    }
}

class DateRangeFlagStrategy private constructor(
    private val from: ReferencedDataType<LocalDate>,
    private val until: ReferencedDataType<LocalDate> = ReferencedDataType { LocalDate.MAX },
) : FeatureFlagStrategy {
    constructor(fromValueProvider: () -> LocalDate, untilValueProvider: () -> LocalDate) :
        this(ReferencedDataType(fromValueProvider), ReferencedDataType(untilValueProvider))

    override fun isEnabled(): Boolean {
        val current = LocalDate.now()

        return current in from.value..<until.value
    }
}
