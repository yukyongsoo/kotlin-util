package com.yuk.common.querydsl

import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.core.types.dsl.NumberExpression
import com.querydsl.core.types.dsl.NumberPath

infix fun <T> NumberPath<T>.EQUAL(value: T?): BooleanExpression? where T : Number, T : Comparable<*> {
    return if (value == null)
        null
    else eq(value)
}

infix fun <T> NumberPath<T>.EQUAL(value: NumberExpression<T>?): BooleanExpression? where T : Number, T : Comparable<*> {
    return if (value == null)
        null
    else eq(value)
}

infix fun <T> NumberPath<T>.`=`(value: T?): BooleanExpression? where T : Number, T : Comparable<*> {
    return if (value == null)
        null
    else eq(value)
}

infix fun <T> NumberPath<T>.GOE(value: T?): BooleanExpression? where T : Number, T : Comparable<*> {
    return if (value == null)
        null
    else goe(value)
}

infix fun <T> NumberPath<T>.`GREATERTHAN`(value: T?): BooleanExpression? where T : Number, T : Comparable<*> {
    return if (value == null)
        null
    else gt(value)
}

infix fun <T> NumberPath<T>.LOE(value: T?): BooleanExpression? where T : Number, T : Comparable<*> {
    return if (value == null)
        null
    else loe(value)
}

infix fun <T> NumberPath<T>.`LESSTHAN`(value: T?): BooleanExpression? where T : Number, T : Comparable<*> {
    return if (value == null)
        null
    else lt(value)
}

infix fun <T> NumberPath<T>.NOTEQUAL(value: NumberExpression<T>?): BooleanExpression? where T : Number, T : Comparable<*> {
    return if (value == null)
        null
    else ne(value)
}

infix fun <T> NumberPath<T>.IN(value: Collection<T>?): BooleanExpression? where T : Number, T : Comparable<*> {
    return if (value == null)
        null
    else `in`(value)
}

infix fun <T> NumberPath<T>.NOTIN(value: Collection<T>?): BooleanExpression? where T : Number, T : Comparable<*> {
    return if (value == null)
        null
    else notIn(value)
}

infix fun <T> NumberPath<T>.BETWEEN(value: IntRange?): BooleanExpression? where T : Number, T : Comparable<*> {
    return if (value == null)
        null
    else between(value.first, value.last)
}
