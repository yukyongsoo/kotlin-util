package com.yuk.common.querydsl

import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.core.types.dsl.BooleanPath

infix fun BooleanExpression.EQUAL(value: Boolean?): BooleanExpression? =
    if (value == null) {
        null
    } else {
        eq(value)
    }

infix fun BooleanExpression.NOTEQUAL(value: Boolean?): BooleanExpression? =
    if (value == null) {
        null
    } else {
        ne(value)
    }

infix fun BooleanExpression.EQUAL(value: BooleanExpression?): BooleanExpression? =
    if (value == null) {
        null
    } else {
        eq(value)
    }

infix fun BooleanExpression.NOTEQUAL(value: BooleanExpression?): BooleanExpression? =
    if (value == null) {
        null
    } else {
        ne(value)
    }
