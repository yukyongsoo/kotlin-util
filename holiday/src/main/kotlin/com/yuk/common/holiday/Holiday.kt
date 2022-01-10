package com.yuk.common.holiday

import java.time.LocalDate

class Holiday(
    val dateName: String,
    val localDate: LocalDate
) {
    override fun hashCode(): Int {
        return localDate.hashCode()
    }

    override fun toString(): String {
        return localDate.toString()
    }

    override fun equals(other: Any?): Boolean {
        other ?: return false

        if (this === other)
            return true

        if (other is Holiday) {
            return localDate == other.localDate
        }

        return false
    }
}
