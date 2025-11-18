package com.yuk.common.querydsl

import com.querydsl.core.types.Predicate
import com.querydsl.core.types.dsl.CaseBuilder
import com.querydsl.core.types.dsl.SimpleExpression

interface CaseScope {
    fun WHEN(predicate: Predicate): CaseBuilder.Initial
}

inline fun <T> case(block: CaseScope.() -> SimpleExpression<T>): SimpleExpression<T> {
    val scope =
        object : CaseScope {
            override fun WHEN(predicate: Predicate) = CaseBuilder().`when`(predicate)
        }

    return scope.block()
}

inline infix fun <reified T> CaseBuilder.Cases<T, SimpleExpression<T>>.WHEN(predicate: Predicate): CaseBuilder.CaseWhen<T, SimpleExpression<T>> = this.`when`(predicate)

inline infix fun <reified T> CaseBuilder.Initial.THEN(value: T): CaseBuilder.Cases<T, SimpleExpression<T>> = this.then(value)

inline infix fun <reified T> CaseBuilder.CaseWhen<T, SimpleExpression<T>>.THEN(value: T): CaseBuilder.Cases<T, SimpleExpression<T>> = this.then(value)

inline infix fun <reified T> CaseBuilder.Cases<T, SimpleExpression<T>>.ELSE(value: T): SimpleExpression<T> = this.otherwise(value)
