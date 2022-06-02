package com.yuk.common.querydsl

import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.core.types.dsl.StringPath

infix fun StringPath.EQUAL(value: String?): BooleanExpression? {
    return if (value == null)
        null
    else eq(value)
}

infix fun StringPath.`=`(value: String?): BooleanExpression? {
    return if (value == null)
        null
    else `in`(value)
}

infix fun StringPath.IN(value: Collection<String>?): BooleanExpression? {
    return if (value == null)
        null
    else `in`(value)
}

infix fun StringPath.NOTIN(value: Collection<String>?): BooleanExpression? {
    return if (value == null)
        null
    else notIn(value)
}

infix fun StringPath.LIKE(value: String?): BooleanExpression? {
    return if (value == null)
        null
    else like(value)
}

infix fun StringPath.NotLike(value: String?): BooleanExpression? {
    return if (value == null)
        null
    else notLike(value)
}
