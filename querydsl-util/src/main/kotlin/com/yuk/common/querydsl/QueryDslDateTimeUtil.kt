package com.yuk.common.querydsl

import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.core.types.dsl.DatePath
import com.querydsl.core.types.dsl.DateTimePath
import java.time.LocalDate
import java.time.LocalDateTime

infix fun DateTimePath<LocalDateTime>.EQUAL(localDateTime: LocalDateTime?): BooleanExpression? {
    return eq(localDateTime)
}
infix fun DateTimePath<LocalDateTime>.BEFORE(localDateTime: LocalDateTime?): BooleanExpression? {
    return before(localDateTime)
}

infix fun DateTimePath<LocalDateTime>.AFTER(localDateTime: LocalDateTime?): BooleanExpression? {
    return after(localDateTime)
}

infix fun DateTimePath<LocalDateTime>.BETWEEN(value: Pair<LocalDateTime?, LocalDateTime?>?): BooleanExpression? {
    return if (value?.first == null || value.second == null)
        null
    else between(value.first, value.second)
}

infix fun DatePath<LocalDate>.BETWEEN(value: Pair<LocalDate?, LocalDate?>?): BooleanExpression? {
    return if (value?.first == null || value.second == null)
        null
    else between(value.first, value.second)
}
