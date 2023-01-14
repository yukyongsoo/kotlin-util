package com.yuk.common.func

import kotlin.reflect.KClass

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
@MustBeDocumented
annotation class WeightedFunction(
    val weight: Int,
    val otherFunctionName: String,
    val otherClass: KClass<*>
)
