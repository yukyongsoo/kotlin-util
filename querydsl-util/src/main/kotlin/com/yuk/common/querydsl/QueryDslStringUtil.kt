package com.yuk.common.querydsl

import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.core.types.dsl.StringExpression
import com.querydsl.core.types.dsl.StringPath

infix fun StringExpression.EQUAL(value: String?): BooleanExpression? =
    if (value == null) {
        null
    } else {
        eq(value)
    }

infix fun StringExpression.EQUAL(value: StringExpression?): BooleanExpression? =
    if (value == null) {
        null
    } else {
        eq(value)
    }

infix fun StringExpression.NOTEQUAL(value: String?): BooleanExpression? =
    if (value == null) {
        null
    } else {
        ne(value)
    }

infix fun StringExpression.NOTEQUAL(value: StringExpression?): BooleanExpression? =
    if (value == null) {
        null
    } else {
        ne(value)
    }

infix fun StringExpression.`=`(value: String?): BooleanExpression? =
    if (value == null) {
        null
    } else {
        `in`(value)
    }

infix fun StringExpression.`=`(value: StringExpression?): BooleanExpression? =
    if (value == null) {
        null
    } else {
        `in`(value)
    }

infix fun StringExpression.IN(value: Collection<String>?): BooleanExpression? =
    if (value == null) {
        null
    } else {
        `in`(value)
    }

infix fun StringExpression.NOTIN(value: Collection<String>?): BooleanExpression? =
    if (value == null) {
        null
    } else {
        notIn(value)
    }

infix fun StringExpression.LIKE(value: String?): BooleanExpression? =
    if (value == null) {
        null
    } else {
        like(value)
    }

infix fun StringExpression.NotLike(value: String?): BooleanExpression? =
    if (value == null) {
        null
    } else {
        notLike(value)
    }
