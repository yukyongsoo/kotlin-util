package com.yuk.common.querydsl

import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.core.types.dsl.BooleanPath

infix fun BooleanPath.EQUAL(value: Boolean?): BooleanExpression? =
    if (value == null) {
        null
    } else {
        eq(value)
    }

infix fun BooleanPath.NOTEQUAL(value: Boolean?): BooleanExpression? =
    if (value == null) {
        null
    } else {
        ne(value)
    }

infix fun BooleanPath.EQUAL(value: BooleanExpression?): BooleanExpression? =
    if (value == null) {
        null
    } else {
        eq(value)
    }

infix fun BooleanPath.NOTEQUAL(value: BooleanExpression?): BooleanExpression? =
    if (value == null) {
        null
    } else {
        ne(value)
    }
