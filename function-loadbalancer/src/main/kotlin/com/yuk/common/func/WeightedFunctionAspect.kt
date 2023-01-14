package com.yuk.common.func

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.reflect.MethodSignature
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Component
import java.lang.reflect.Method
import kotlin.random.Random

@Aspect
@Component
class WeightedFunctionAspect(
    private val context: ApplicationContext
) {
    @Around("@annotation(weightedFunction)")
    @Throws(Throwable::class)
    fun around(
        joinPoint: ProceedingJoinPoint,
        weightedFunction: WeightedFunction
    ): Any? {
        val weight = weightedFunction.weight

        return if (Random.nextInt(100) < weight) {
            joinPoint.proceed()
        } else {
            runOther(joinPoint, weightedFunction)
        }
    }

    private fun runOther(
        joinPoint: ProceedingJoinPoint,
        weightedFunction: WeightedFunction
    ): Any? {
        val targetBean = getTargetBean(weightedFunction.otherClass.java)

        val otherMethod = checkSameParamAndReturn(
            joinPoint.signature as MethodSignature,
            targetBean,
            weightedFunction.otherFunctionName
        )

        return otherMethod.invoke(targetBean, *joinPoint.args)
    }

    private fun getTargetBean(serviceClass: Class<out Any>): Any {
        val className = serviceClass.simpleName.replaceFirstChar { it.lowercase() }

        if (context.containsBean(className)) {
            return context.getBean(className)
        }

        if (context.containsBean(serviceClass.name))
            return context.getBean(serviceClass.name)

        throw IllegalArgumentException("${serviceClass.name} is not a Spring bean")
    }

    private fun checkSameParamAndReturn(
        signature: MethodSignature,
        targetBean: Any,
        otherFunctionName: String
    ): Method {
        return try {
            val method = targetBean.javaClass.getMethod(
                otherFunctionName,
                *signature.parameterTypes
            )

            if (signature.method.returnType != method.returnType)
                throw IllegalArgumentException("$otherFunctionName is not same original method. routing method has same return type?")

            method
        } catch (e: NoSuchMethodException) {
            throw IllegalArgumentException("$otherFunctionName is not same original method. routing method has same parameter?")
        }
    }
}
