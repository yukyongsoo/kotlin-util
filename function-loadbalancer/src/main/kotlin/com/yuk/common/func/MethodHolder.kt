package com.yuk.common.func

import org.aspectj.lang.reflect.MethodSignature
import org.springframework.aop.framework.AopProxyUtils
import java.lang.reflect.Method
import kotlin.reflect.KClass

data class MethodHolder(
    private val klass: Class<*>,
    private val method: Method,
    private val instance: Any,
) {
    constructor(
        klass: Class<*>,
        method: Method,
        instanceProvider: InstanceProvider,
    ) : this(klass, method, instanceProvider.getInstance())

    fun execute(
        vararg args: Any?,
    ): Any? {
        val result = method.invoke(instance, *args)
        return result
    }

    companion object {
        fun fromAspect(
            signature: MethodSignature,
            otherClass: KClass<*>,
            otherFunctionName: String,
            currentBean: Any,
            secondaryBean: Any,
        ): List<MethodHolder> {
            val otherMethod =
                secondaryBean.javaClass.getMethod(
                    otherFunctionName,
                    *signature.parameterTypes,
                )

            otherMethod.annotations
                .find { it is DistributeCall }
                ?.let { throw IllegalArgumentException("Other methods cannot have the DistributeCall annotation") }

            checkSameParamAndReturn(signature, otherClass, otherFunctionName)

            return listOf(
                MethodHolder(signature.declaringType, signature.method, currentBean),
                MethodHolder(otherClass.java, otherMethod, secondaryBean),
            )
        }

        private fun checkSameParamAndReturn(
            signature: MethodSignature,
            otherClass: KClass<*>,
            otherFunctionName: String,
        ): Method =
            try {
                val method =
                    otherClass.java.getMethod(
                        otherFunctionName,
                        *signature.parameterTypes,
                    )

                if (signature.method.returnType != method.returnType) {
                    throw IllegalArgumentException("$otherFunctionName is not same original method. routing method has same return type?")
                }

                method
            } catch (e: NoSuchMethodException) {
                throw IllegalArgumentException("$otherFunctionName is not same original method. routing method has same parameter?")
            }
    }
}
