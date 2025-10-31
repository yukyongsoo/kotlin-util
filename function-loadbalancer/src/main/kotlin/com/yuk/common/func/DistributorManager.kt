package com.yuk.common.func

import java.util.concurrent.ConcurrentHashMap

object DistributorManager {
    private val distributorMap = ConcurrentHashMap<String, Distributor>()

    fun add(
        name: String,
        distributor: Distributor,
        strategyName: String,
    ) {
        DistributorRatioManager.get(strategyName)

        distributorMap[name] = distributor
    }

    fun get(name: String) = distributorMap[name]

    fun remove(name: String) = distributorMap.remove(name)
}
