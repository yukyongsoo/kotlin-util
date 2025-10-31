package com.yuk.common.func

interface DistributorRatioStrategy {
    fun determine(methodHolders: List<MethodHolder>): MethodHolder

    fun <T> determine(
        primaryFunc: () -> T,
        secondaryFunc: () -> T,
    ): () -> T

    fun addSuccessCount(number: Int)

    fun addFailCount(number: Int)

    fun resetCount()
}
