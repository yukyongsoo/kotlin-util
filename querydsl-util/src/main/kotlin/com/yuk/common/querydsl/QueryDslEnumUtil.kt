package com.yuk.common.querydsl

import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.core.types.dsl.EnumExpression
import com.querydsl.core.types.dsl.EnumPath

infix fun <T : Enum<T>> EnumPath<T>.EQUAL(value: T?): BooleanExpression? {
    return if (value == null)
        null
    else eq(value)
}

infix fun <T : Enum<T>> EnumPath<T>.EQUAL(value: EnumExpression<T>?): BooleanExpression? {
    return if (value == null)
        null
    else eq(value)
}

infix fun <T : Enum<T>> EnumPath<T>.NOTEQUAL(value: T?): BooleanExpression? {
    return if (value == null)
        null
    else ne(value)
}

infix fun <T : Enum<T>> EnumPath<T>.NOTEQUAL(value: EnumExpression<T>?): BooleanExpression? {
    return if (value == null)
        null
    else ne(value)
}

infix fun <T : Enum<T>> EnumPath<T>.IN(value: Collection<T>?): BooleanExpression? {
    return if (value == null)
        null
    else `in`(value)
}

infix fun <T : Enum<T>> EnumPath<T>.NOTIN(value: Collection<T>?): BooleanExpression? {
    return if (value == null)
        null
    else notIn(value)
}
