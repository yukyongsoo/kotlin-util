package com.yuk.common.func

import java.util.concurrent.atomic.AtomicInteger

class TimeBasedDistributorRatio(
    private var primaryCallPercent: Int,
    private val secondaryIncreaseRatio: Int,
    val durationMiles: Int,
) : DistributorRatioStrategy,
    Runnable {
    private val callCount = AtomicInteger(0)
    private val primaryCount = AtomicInteger(0)

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

    override fun run() {
        if (primaryCallPercent == 0) {
            return
        }

        primaryCallPercent = (primaryCallPercent - secondaryIncreaseRatio).coerceAtLeast(0)
    }
}
