package com.yuk.common.querydsl

import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.core.types.dsl.EnumExpression
import com.querydsl.core.types.dsl.EnumPath

infix fun <T : Enum<T>> EnumExpression<T>.EQUAL(value: T?): BooleanExpression? =
    if (value == null) {
        null
    } else {
        eq(value)
    }

infix fun <T : Enum<T>> EnumExpression<T>.EQUAL(value: EnumExpression<T>?): BooleanExpression? =
    if (value == null) {
        null
    } else {
        eq(value)
    }

infix fun <T : Enum<T>> EnumExpression<T>.NOTEQUAL(value: T?): BooleanExpression? =
    if (value == null) {
        null
    } else {
        ne(value)
    }

infix fun <T : Enum<T>> EnumExpression<T>.NOTEQUAL(value: EnumExpression<T>?): BooleanExpression? =
    if (value == null) {
        null
    } else {
        ne(value)
    }

infix fun <T : Enum<T>> EnumExpression<T>.IN(value: Collection<T>?): BooleanExpression? =
    if (value == null) {
        null
    } else {
        `in`(value)
    }

infix fun <T : Enum<T>> EnumExpression<T>.NOTIN(value: Collection<T>?): BooleanExpression? =
    if (value == null) {
        null
    } else {
        notIn(value)
    }
