package com.yuk.common.querydsl

import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.core.types.dsl.BooleanPath

infix fun BooleanPath.EQUAL(value: Boolean?): BooleanExpression? {
    return if (value == null)
        null
    else eq(value)
}

infix fun BooleanPath.NOTEQUAL(value: Boolean?): BooleanExpression? {
    return if (value == null)
        null
    else ne(value)
}