package com.yuk.common.func

import java.util.concurrent.atomic.AtomicInteger

class FixedDistributorRatio(
    private val primaryCallPercent: Int,
) : DistributorRatioStrategy {
    private val callCount = AtomicInteger(0)
    private val primaryCount = AtomicInteger(0)

    init {
        if (primaryCallPercent !in 0..100) {
            throw IllegalArgumentException("primaryCallWeight must be between 0 and 100")
        }
    }

    override fun determine(methodHolders: List<MethodHolder>): MethodHolder {
        val currentCall = callCount.incrementAndGet()
        val currentPrimary = primaryCount.get()

        // 현재까지 primary가 호출되어야 할 이상적인 횟수. +50은 반올림 효과
        val expectedPrimaryCount = (currentCall * primaryCallPercent + 50) / 100

        return if (currentPrimary < expectedPrimaryCount) {
            primaryCount.incrementAndGet()
            methodHolders.first()
        } else {
            methodHolders.last()
        }
    }

    override fun <T> determine(
        primaryFunc: () -> T,
        secondaryFunc: () -> T,
    ): () -> T {
        val currentCall = callCount.incrementAndGet()
        val currentPrimary = primaryCount.get()

        // 현재까지 primary가 호출되어야 할 이상적인 횟수. +50은 반올림 효과
        val expectedPrimaryCount = (currentCall * primaryCallPercent + 50) / 100

        return if (currentPrimary < expectedPrimaryCount) {
            primaryCount.incrementAndGet()
            primaryFunc
        } else {
            secondaryFunc
        }
    }

    override fun addSuccessCount(number: Int) {
    }

    override fun addFailCount(number: Int) {
    }

    override fun resetCount() {
    }
}
