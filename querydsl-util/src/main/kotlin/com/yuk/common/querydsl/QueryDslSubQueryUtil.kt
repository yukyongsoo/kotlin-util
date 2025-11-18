package com.yuk.common.querydsl

import com.querydsl.core.types.EntityPath
import com.querydsl.core.types.Expression
import com.querydsl.core.types.Predicate
import com.querydsl.jpa.JPAExpressions
import com.querydsl.jpa.JPQLSubQuery

interface SubqueryScope {
    fun <T> SELECT(expr: Expression<T>): JPQLSubQuery<T>

    fun <T> `SELECT DISTINCT`(expr: Expression<T>): JPQLSubQuery<T>
}

inline fun <T> subquery(block: SubqueryScope.() -> JPQLSubQuery<T>): JPQLSubQuery<T> {
    val scope =
        object : SubqueryScope {
            override fun <R> SELECT(expr: Expression<R>): JPQLSubQuery<R> = JPAExpressions.select(expr)

            override fun <T> `SELECT DISTINCT`(expr: Expression<T>): JPQLSubQuery<T> = JPAExpressions.selectDistinct(expr)
        }

    return scope.block()
}

infix fun <T, V> JPQLSubQuery<T>.FROM(entityPath: EntityPath<V>): JPQLSubQuery<T> = this.from(entityPath)

inline infix fun <T> JPQLSubQuery<T>.WHERE(function: () -> Predicate?): JPQLSubQuery<T> {
    val predicate = function()
    return if (predicate == null) {
        this
    } else {
        where(predicate)
    }
}
