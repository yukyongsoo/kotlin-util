package com.yuk.common.feature

internal class ReferencedDataType<T>(
    private val valueProvider: () -> T,
) {
    val value: T
        get() = valueProvider()
}
