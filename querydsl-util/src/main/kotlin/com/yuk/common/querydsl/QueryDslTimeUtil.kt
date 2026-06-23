package com.yuk.common.querydsl

import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.core.types.dsl.DateTimeExpression
import com.querydsl.core.types.dsl.TimeExpression
import java.time.LocalTime

infix fun TimeExpression<LocalTime>.EQUAL(localTime: LocalTime?): BooleanExpression? =
    if (localTime == null) {
        null
    } else {
        eq(localTime)
    }

infix fun TimeExpression<LocalTime>.EQUAL(localTime: DateTimeExpression<LocalTime>?): BooleanExpression? =
    if (localTime == null) {
        null
    } else {
        eq(localTime)
    }

infix fun TimeExpression<LocalTime>.BEFORE(localTime: LocalTime?): BooleanExpression? =
    if (localTime == null) {
        null
    } else {
        before(localTime)
    }

infix fun TimeExpression<LocalTime>.BEFORE(localTime: DateTimeExpression<LocalTime>?): BooleanExpression? =
    if (localTime == null) {
        null
    } else {
        before(localTime)
    }

infix fun TimeExpression<LocalTime>.AFTER(localTime: LocalTime?): BooleanExpression? =
    if (localTime == null) {
        null
    } else {
        after(localTime)
    }

infix fun TimeExpression<LocalTime>.AFTER(localTime: DateTimeExpression<LocalTime>?): BooleanExpression? =
    if (localTime == null) {
        null
    } else {
        after(localTime)
    }

infix fun TimeExpression<LocalTime>.BETWEEN(value: Pair<LocalTime?, LocalTime?>?): BooleanExpression? =
    if (value?.first == null || value.second == null) {
        null
    } else {
        between(value.first, value.second)
    }
