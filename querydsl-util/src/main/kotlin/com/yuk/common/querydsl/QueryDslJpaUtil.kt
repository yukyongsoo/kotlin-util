package com.yuk.common.querydsl

import com.querydsl.core.types.EntityPath
import com.querydsl.core.types.Predicate
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.core.types.dsl.BooleanPath
import com.querydsl.core.types.dsl.DatePath
import com.querydsl.core.types.dsl.DateTimePath
import com.querydsl.core.types.dsl.EnumPath
import com.querydsl.core.types.dsl.NumberPath
import com.querydsl.core.types.dsl.StringPath
import com.querydsl.jpa.JPQLQuery
import com.querydsl.jpa.impl.AbstractJPAQuery
import com.querydsl.jpa.impl.JPAQuery
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.support.Querydsl
import java.time.LocalDate
import java.time.LocalDateTime

infix fun <T> Querydsl?.select(entityPath: EntityPath<T>): AbstractJPAQuery<T, JPAQuery<T>> {
    return this!!.createQuery<T>().from(entityPath)
}

inline infix fun <T> JPQLQuery<T>.where(function: () -> Predicate?): JPQLQuery<T> {
    val predicate = function()
    return if (predicate == null)
        this
    else
        where(predicate)
}

infix fun BooleanExpression?.and(expression: BooleanExpression?): BooleanExpression? {
    return if (this != null && expression != null)
        and(expression)
    else this ?: expression
}

infix fun BooleanExpression?.or(expression: BooleanExpression?): BooleanExpression? {
    return if (this != null && expression != null)
        or(expression)
    else this ?: expression
}

infix fun <T> JPQLQuery<T>.fetchJoin(entityPath: EntityPath<*>): JPQLQuery<T> {
    return fetchJoin().innerJoin(entityPath)
}

infix fun <T> JPQLQuery<T>.join(entityPath: EntityPath<*>): JPQLQuery<T> {
    return innerJoin(entityPath)
}

infix fun <T> JPQLQuery<T>.fetchLeftJoin(entityPath: EntityPath<*>): JPQLQuery<T> {
    return fetchJoin().leftJoin(entityPath)
}

infix fun <T> JPQLQuery<T>.leftJoin(entityPath: EntityPath<*>): JPQLQuery<T> {
    return leftJoin(entityPath)
}

infix fun <T> JPQLQuery<T>.fetchRightJoin(entityPath: EntityPath<*>): JPQLQuery<T> {
    return fetchJoin().leftJoin(entityPath)
}

infix fun <T> JPQLQuery<T>.rightJoin(entityPath: EntityPath<*>): JPQLQuery<T> {
    return rightJoin(entityPath)
}

infix fun <T> JPQLQuery<T>.on(booleanExpression: BooleanExpression?): JPQLQuery<T> {
    return on(booleanExpression)
}

infix fun <T> JPQLQuery<T>.pagingBy(pageable: Pageable): JPQLQuery<T> {
    return offset(pageable.offset).limit(pageable.pageSize.toLong())
}

infix fun StringPath.equal(value: String?): BooleanExpression? {
    return if (value == null)
        null
    else eq(value)
}

infix fun StringPath.`in`(value: Collection<String>?): BooleanExpression? {
    return if (value == null)
        null
    else `in`(value)
}

infix fun StringPath.notIn(value: Collection<String>?): BooleanExpression? {
    return if (value == null)
        null
    else notIn(value)
}

infix fun StringPath.like(value: String?): BooleanExpression? {
    return if (value == null)
        null
    else likeIgnoreCase(value)
}

infix fun StringPath.contains(value: String?): BooleanExpression? {
    return if (value != null)
        containsIgnoreCase(value)
    else null
}

infix fun <T> NumberPath<T>.equal(value: T?): BooleanExpression? where T : Number, T : Comparable<*> {
    return if (value == null)
        null
    else eq(value)
}

infix fun <T> NumberPath<T>.`in`(value: Collection<T>?): BooleanExpression? where T : Number, T : Comparable<*> {
    return if (value == null)
        null
    else `in`(value)
}

infix fun <T> NumberPath<T>.notIn(value: Collection<T>?): BooleanExpression? where T : Number, T : Comparable<*> {
    return if (value == null)
        null
    else notIn(value)
}

infix fun <T> NumberPath<T>.between(value: IntRange?): BooleanExpression? where T : Number, T : Comparable<*> {
    return if (value == null)
        null
    else between(value.first, value.last)
}

infix fun <T : Enum<T>> EnumPath<T>.equal(value: T?): BooleanExpression? {
    return if (value == null)
        null
    else eq(value)
}

infix fun <T : Enum<T>> EnumPath<T>.`in`(value: Collection<T>?): BooleanExpression? {
    return if (value == null)
        null
    else `in`(value)
}

infix fun BooleanPath.equal(value: Boolean?): BooleanExpression? {
    return if (value == null)
        null
    else eq(value)
}

infix fun DateTimePath<LocalDateTime>.before(localDateTime: LocalDateTime?): BooleanExpression? {
    return before(localDateTime)
}

infix fun DateTimePath<LocalDateTime>.after(localDateTime: LocalDateTime?): BooleanExpression? {
    return after(localDateTime)
}

infix fun DateTimePath<LocalDateTime>.between(value: Pair<LocalDateTime?, LocalDateTime?>?): BooleanExpression? {
    return if (value?.first == null || value.second == null)
        null
    else between(value.first, value.second)
}

infix fun DatePath<LocalDate>.between(value: Pair<LocalDate?, LocalDate?>?): BooleanExpression? {
    return if (value?.first == null || value.second == null)
        null
    else between(value.first, value.second)
}
