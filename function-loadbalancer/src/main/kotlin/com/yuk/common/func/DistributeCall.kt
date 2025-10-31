package com.yuk.common.func

import kotlin.reflect.KClass

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
@MustBeDocumented
annotation class DistributeCall(
    val name: String,
    val otherFunctionName: String,
    val otherClass: KClass<*>,
    val strategyName: String,
)
