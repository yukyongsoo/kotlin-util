package com.yuk.common.func

fun <T> distribute(
    strategyName: String,
    primaryFunc: () -> T,
    secondaryFunc: () -> T,
): T {
    val strategy = DistributorRatioManager.get(strategyName)

    val func = strategy.determine(primaryFunc, secondaryFunc)

    return func.invoke()
}
