package com.yuk.common.querydsl.blaze

import com.blazebit.persistence.querydsl.BlazeJPAQuery
import com.querydsl.core.types.EntityPath
import com.querydsl.core.types.Expression
import com.querydsl.core.types.Projections
import com.querydsl.jpa.JPQLQuery

infix fun <T> BlazeJPAQuery<T>.SELECT(entityPath: EntityPath<T>): JPQLQuery<T> {
    return this.select(entityPath)
}

inline infix fun <reified T : Any> BlazeJPAQuery<T>.SELECT(function: () -> Array<Expression<out Any>>): JPQLQuery<T> {
    val projection = Projections.constructor(
        T::class.java,
        *function()
    )

    return this.select(projection)
}