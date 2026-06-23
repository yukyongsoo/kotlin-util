package com.yuk.common.querydsl

import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.core.types.dsl.DateExpression
import com.querydsl.core.types.dsl.DatePath
import com.querydsl.core.types.dsl.DateTimeExpression
import com.querydsl.core.types.dsl.DateTimePath
import java.time.LocalDate
import java.time.LocalDateTime

infix fun DateTimeExpression<LocalDateTime>.EQUAL(localDateTime: LocalDateTime?): BooleanExpression? =
    if (localDateTime == null) {
        null
    } else {
        eq(localDateTime)
    }

infix fun DateTimeExpression<LocalDateTime>.EQUAL(localDateTime: DateTimeExpression<LocalDateTime>?): BooleanExpression? =
    if (localDateTime == null) {
        null
    } else {
        eq(localDateTime)
    }

infix fun DateTimeExpression<LocalDateTime>.BEFORE(localDateTime: LocalDateTime?): BooleanExpression? =
    if (localDateTime == null) {
        null
    } else {
        before(localDateTime)
    }

infix fun DateTimeExpression<LocalDateTime>.BEFORE(localDateTime: DateTimeExpression<LocalDateTime>?): BooleanExpression? =
    if (localDateTime == null) {
        null
    } else {
        before(localDateTime)
    }

infix fun DateTimeExpression<LocalDateTime>.AFTER(localDateTime: LocalDateTime?): BooleanExpression? =
    if (localDateTime == null) {
        null
    } else {
        after(localDateTime)
    }

infix fun DateTimeExpression<LocalDateTime>.AFTER(localDateTime: DateTimeExpression<LocalDateTime>?): BooleanExpression? =
    if (localDateTime == null) {
        null
    } else {
        after(localDateTime)
    }

infix fun DateTimeExpression<LocalDateTime>.BETWEEN(value: Pair<LocalDateTime?, LocalDateTime?>?): BooleanExpression? =
    if (value?.first == null || value.second == null) {
        null
    } else {
        between(value.first, value.second)
    }
