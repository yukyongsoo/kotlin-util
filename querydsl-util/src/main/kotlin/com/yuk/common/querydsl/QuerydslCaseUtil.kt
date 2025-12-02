package com.yuk.common.querydsl

import com.querydsl.core.types.Expression
import com.querydsl.core.types.Predicate
import com.querydsl.core.types.dsl.CaseBuilder
import com.querydsl.core.types.dsl.SimpleExpression

interface CaseScope {
    fun WHEN(predicate: Predicate): CaseBuilder.Initial
}

inline fun <A, T : Expression<A>> case(block: CaseScope.() -> T): T {
    val scope =
        object : CaseScope {
            override fun WHEN(predicate: Predicate) = CaseBuilder().`when`(predicate)
        }

    return scope.block()
}

inline infix fun <A, T : Expression<A>> CaseBuilder.Initial.THEN(value: T) = this.then(value)

inline infix fun <A, T : Expression<A>> CaseBuilder.Initial.THEN(value: A) = this.then(value)

inline infix fun <A, T : Expression<A>> CaseBuilder.Cases<A, T>.WHEN(predicate: Predicate) = this.`when`(predicate)

inline infix fun <A, T : Expression<A>> CaseBuilder.CaseWhen<A, T>.THEN(value: A) = this.then(value)

inline infix fun <A, T : Expression<A>> CaseBuilder.CaseWhen<A, T>.THEN(value: T) = this.then(value)

inline infix fun <A, T : Expression<A>> CaseBuilder.Cases<A, T>.ELSE(value: A) = this.otherwise(value)
