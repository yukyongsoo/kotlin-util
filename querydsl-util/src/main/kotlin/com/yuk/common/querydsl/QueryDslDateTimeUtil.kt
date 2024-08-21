package com.yuk.common.querydsl

import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.core.types.dsl.DatePath
import com.querydsl.core.types.dsl.DateTimeExpression
import com.querydsl.core.types.dsl.DateTimePath
import java.time.LocalDate
import java.time.LocalDateTime

infix fun DateTimePath<LocalDateTime>.EQUAL(localDateTime: LocalDateTime?): BooleanExpression? = eq(localDateTime)

infix fun DateTimePath<LocalDateTime>.EQUAL(localDateTime: DateTimeExpression<LocalDateTime>?): BooleanExpression? = eq(localDateTime)

infix fun DateTimePath<LocalDateTime>.BEFORE(localDateTime: LocalDateTime?): BooleanExpression? = before(localDateTime)

infix fun DateTimePath<LocalDateTime>.BEFORE(localDateTime: DateTimeExpression<LocalDateTime>?): BooleanExpression? = before(localDateTime)

infix fun DateTimePath<LocalDateTime>.AFTER(localDateTime: LocalDateTime?): BooleanExpression? = after(localDateTime)

infix fun DateTimePath<LocalDateTime>.AFTER(localDateTime: DateTimeExpression<LocalDateTime>?): BooleanExpression? = after(localDateTime)

infix fun DateTimePath<LocalDateTime>.BETWEEN(value: Pair<LocalDateTime?, LocalDateTime?>?): BooleanExpression? =
    if (value?.first == null || value.second == null) {
        null
    } else {
        between(value.first, value.second)
    }

infix fun DatePath<LocalDate>.BETWEEN(value: Pair<LocalDate?, LocalDate?>?): BooleanExpression? =
    if (value?.first == null || value.second == null) {
        null
    } else {
        between(value.first, value.second)
    }
