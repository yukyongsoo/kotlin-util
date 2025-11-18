package com.yuk.common.querydsl

import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.core.types.dsl.DateExpression
import com.querydsl.core.types.dsl.DateTimeExpression
import java.time.LocalDate
import java.time.LocalDateTime

infix fun DateExpression<LocalDate>.EQUAL(localDate: LocalDate?): BooleanExpression? = eq(localDate)

infix fun DateExpression<LocalDate>.EQUAL(localDate: DateTimeExpression<LocalDate>?): BooleanExpression? = eq(localDate)

infix fun DateExpression<LocalDate>.BEFORE(localDate: LocalDate?): BooleanExpression? = before(localDate)

infix fun DateExpression<LocalDate>.BEFORE(localDate: DateTimeExpression<LocalDate>?): BooleanExpression? = before(localDate)

infix fun DateExpression<LocalDate>.AFTER(localDate: LocalDate?): BooleanExpression? = after(localDate)

infix fun DateExpression<LocalDate>.AFTER(localDate: DateTimeExpression<LocalDate>?): BooleanExpression? = after(localDate)

infix fun DateExpression<LocalDate>.BETWEEN(value: Pair<LocalDate?, LocalDate?>?): BooleanExpression? =
    if (value?.first == null || value.second == null) {
        null
    } else {
        between(value.first, value.second)
    }
