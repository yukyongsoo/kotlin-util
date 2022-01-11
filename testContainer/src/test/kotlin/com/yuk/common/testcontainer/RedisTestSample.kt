package com.yuk.common.testcontainer

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension

class RedisTestSample {
    companion object {
        init {
            RedisTestContainer.initialize(
                listOf("spring.redis.host"),
                listOf("spring.redis.port")
            )
        }

        @JvmField
        @RegisterExtension
        val redis = RedisTestContainer()
    }

    @Test
    fun test() {
    }
}
