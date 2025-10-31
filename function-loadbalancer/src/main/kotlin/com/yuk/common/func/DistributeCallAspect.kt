package com.yuk.common.func

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.reflect.MethodSignature
import org.springframework.aop.framework.AopProxyUtils
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Component

@Aspect
@Component
class DistributeCallAspect(
    private val context: ApplicationContext,
) {
    @Around("@annotation(distributeCall)")
    @Throws(Throwable::class)
    fun around(
        joinPoint: ProceedingJoinPoint,
        distributeCall: DistributeCall,
    ): Any? {
        val distributor =
            DistributorManager.get(distributeCall.name)
                ?: let {
                    val currentBean = SpringInstanceProvider(context, joinPoint.`this`.javaClass).getInstance()
                    val secondaryBean = SpringInstanceProvider(context, distributeCall.otherClass.java).getInstance()

                    Distributor.fromAnnotation(distributeCall, joinPoint.signature as MethodSignature, currentBean, secondaryBean).apply {
                        DistributorManager.add(distributeCall.name, this, distributeCall.strategyName)
                    }
                }

        return distributor.execute(joinPoint.args)
    }
}
