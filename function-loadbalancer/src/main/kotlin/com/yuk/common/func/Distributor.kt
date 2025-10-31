package com.yuk.common.func

import org.aspectj.lang.reflect.MethodSignature

data class Distributor(
    val name: String,
    val ratioStrategy: DistributorRatioStrategy,
    val methodHolder: List<MethodHolder>,
) {
    fun execute(
        args: Array<Any>,
    ): Any? {
        val methodHolder = ratioStrategy.determine(methodHolder)

        return try {
            methodHolder.execute(*args).apply {
                ratioStrategy.addSuccessCount(1)
            }
        } catch (e: Exception) {
            ratioStrategy.addFailCount(1)
        }
    }

    companion object {
        fun fromAnnotation(
            distributeCall: DistributeCall,
            signature: MethodSignature,
            currentBean: Any,
            secondaryBean: Any,
        ): Distributor =
            Distributor(
                distributeCall.name,
                DistributorRatioManager.get(distributeCall.strategyName),
                MethodHolder.fromAspect(signature, distributeCall.otherClass, distributeCall.otherFunctionName, currentBean, secondaryBean),
            )
    }
}
