package com.yuk.common.func

import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

object DistributorRatioManager {
    private val ratioMap = ConcurrentHashMap<String, DistributorRatioStrategy>()
    private val executorMap = ConcurrentHashMap<String, ScheduledExecutorService>()

    fun add(
        name: String,
        strategy: DistributorRatioStrategy,
    ) {
        ratioMap[name] = strategy

        if (strategy is TimeBasedDistributorRatio) {
            attachExecutor(name, strategy)
        }
    }

    private fun attachExecutor(
        name: String,
        strategy: TimeBasedDistributorRatio,
    ) {
        val scheduler = Executors.newSingleThreadScheduledExecutor()

        scheduler.scheduleAtFixedRate(
            strategy,
            strategy.durationMiles.toLong(),
            strategy.durationMiles.toLong(),
            TimeUnit.MILLISECONDS,
        )

        executorMap[name] = scheduler
    }

    fun get(name: String) = ratioMap[name] ?: throw RuntimeException("No strategy found for name: $name")
}
