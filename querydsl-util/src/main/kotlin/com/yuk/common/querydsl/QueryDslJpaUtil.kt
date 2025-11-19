package com.yuk.common.querydsl

import com.querydsl.core.types.EntityPath
import com.querydsl.core.types.Expression
import com.querydsl.core.types.OrderSpecifier
import com.querydsl.core.types.Path
import com.querydsl.core.types.Predicate
import com.querydsl.core.types.Projections
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.core.types.dsl.ComparableExpression
import com.querydsl.core.types.dsl.SimpleExpression
import com.querydsl.jpa.JPQLQuery
import com.querydsl.jpa.impl.JPAQueryFactory

infix fun <T : Any> JPAQueryFactory.SELECT(expr: Expression<T>): JPQLQuery<T> = this.select(expr)

inline infix fun <reified T : Any> JPAQueryFactory.SELECT(function: () -> Array<Expression<out Any>>): JPQLQuery<T> {
    val projection =
        Projections.constructor(
            T::class.java,
            *function(),
        )

    return this.select(projection)
}

infix fun <T : Any> JPAQueryFactory.`SELECT DISTINCT`(expr: Expression<T>): JPQLQuery<T> = this.selectDistinct(expr)

inline infix fun <reified T : Any> JPAQueryFactory.`SELECT DISTINCT`(function: () -> Array<Expression<out Any>>): JPQLQuery<T> {
    val projection =
        Projections.constructor(
            T::class.java,
            *function(),
        )

    return this.selectDistinct(projection)
}

infix fun <T, V> JPQLQuery<T>.FROM(entityPath: EntityPath<V>): JPQLQuery<T> = this.from(entityPath)

inline infix fun <T> JPQLQuery<T>.WHERE(function: () -> Predicate?): JPQLQuery<T> {
    val predicate = function()
    return if (predicate == null) {
        this
    } else {
        where(predicate)
    }
}

infix fun BooleanExpression?.AND(expression: BooleanExpression?): BooleanExpression? =
    if (this != null && expression != null) {
        and(expression)
    } else {
        this ?: expression
    }

infix fun BooleanExpression?.AND(expression: Collection<BooleanExpression?>?): BooleanExpression? =
    if (this != null && expression != null) {
        expression.fold(this) { current, booleanExpression ->
            booleanExpression?.let { current.and(booleanExpression) } ?: current
        }
    } else {
        this
    }

infix fun BooleanExpression?.AND(function: () -> BooleanExpression?): BooleanExpression? {
    val expression = function()

    return AND(expression)
}

infix fun BooleanExpression?.OR(expression: BooleanExpression?): BooleanExpression? =
    if (this != null && expression != null) {
        or(expression)
    } else {
        this ?: expression
    }

infix fun BooleanExpression?.OR(expression: Collection<BooleanExpression?>?): BooleanExpression? =
    if (this != null && expression != null) {
        expression.fold(this) { current, booleanExpression ->
            booleanExpression?.let { current.or(booleanExpression) } ?: current
        }
    } else {
        this
    }

infix fun BooleanExpression?.OR(function: () -> BooleanExpression?): BooleanExpression? {
    val expression = function()

    return OR(expression)
}

infix fun <T> JPQLQuery<T>.FETCHJOIN(entityPath: EntityPath<*>): JPQLQuery<T> = innerJoin(entityPath).fetchJoin()

infix fun <T> JPQLQuery<T>.JOIN(entityPath: EntityPath<*>): JPQLQuery<T> = innerJoin(entityPath)

infix fun <T> JPQLQuery<T>.FETCHLEFTJOIN(entityPath: EntityPath<*>): JPQLQuery<T> = leftJoin(entityPath).fetchJoin()

infix fun <T> JPQLQuery<T>.LEFTJOIN(entityPath: EntityPath<*>): JPQLQuery<T> = leftJoin(entityPath)

infix fun <T> JPQLQuery<T>.FETCHRIGHTJOIN(entityPath: EntityPath<*>): JPQLQuery<T> = rightJoin(entityPath).fetchJoin()

infix fun <T> JPQLQuery<T>.RIGHTJOIN(entityPath: EntityPath<*>): JPQLQuery<T> = rightJoin(entityPath)

infix fun <T> JPQLQuery<T>.ON(booleanExpression: BooleanExpression?): JPQLQuery<T> = on(booleanExpression)

infix fun <T> JPQLQuery<T>.OFFSET(offset: Long): JPQLQuery<T> = offset(offset)

infix fun <T> JPQLQuery<T>.LIMIT(limit: Long): JPQLQuery<T> = limit(limit)

infix fun <T, V : Comparable<V>> JPQLQuery<T>.ORDERBY(orderSpecifier: OrderSpecifier<V>): JPQLQuery<T> = this.orderBy(orderSpecifier)

infix fun <T> JPQLQuery<T>.ORDERBY(function: () -> Array<out OrderSpecifier<*>>): JPQLQuery<T> = this.orderBy(*function())

infix fun <T> JPQLQuery<T>.GROUPBY(path: Path<*>): JPQLQuery<T> = this.groupBy(path)

infix fun <T> JPQLQuery<T>.GROUPBY(function: () -> Array<Path<*>>): JPQLQuery<T> = this.groupBy(*function())

infix fun <T> JPQLQuery<T>.HAVING(expr: BooleanExpression?): JPQLQuery<T> = this.having(expr)

infix fun <T : Comparable<Nothing>?> ComparableExpression<T>.EQUAL(value: T): BooleanExpression? =
    if (value == null) {
        null
    } else {
        eq(value)
    }

sealed interface NullExpression

object NULL : NullExpression

object `NOT NULL` : NullExpression

infix fun SimpleExpression<*>.`IS`(expression: NullExpression): BooleanExpression {
    return if (expression == NULL) {
        this.isNull
    } else this.isNotNull
}
