package com.yuk.common.func

import java.util.concurrent.ConcurrentHashMap

object DistributorRatioManager {
    private val ratioMap = ConcurrentHashMap<String, DistributorRatioStrategy>()

    fun add(
        name: String,
        strategy: DistributorRatioStrategy,
    ) {
        ratioMap[name] = strategy
    }

    fun get(name: String) = ratioMap[name] ?: throw RuntimeException("No strategy found for name: $name")
}
