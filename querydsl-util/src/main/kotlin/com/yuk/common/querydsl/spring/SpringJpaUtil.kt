package com.yuk.common.querydsl.spring

import com.querydsl.core.types.Expression
import com.querydsl.core.types.Projections
import com.querydsl.jpa.JPQLQuery
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.support.Querydsl

inline infix fun <reified T : Any> Querydsl.SELECT(expr: Expression<T>): JPQLQuery<T> {
    return this.createQuery<T>().select(expr)
}

inline infix fun <reified T : Any> Querydsl.SELECT(function: () -> Array<Expression<out Any>>): JPQLQuery<T> {
    val projection = Projections.constructor(
        T::class.java,
        *function()
    )

    return this.createQuery<T>().select(projection)
}

infix fun <T> JPQLQuery<T>.PAGINGBY(pageable: Pageable): JPQLQuery<T> {
    return offset(pageable.offset).limit(pageable.pageSize.toLong())
}