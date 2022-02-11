package com.yuk.common.testcontainer

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension

class KafkaTestSample {
    companion object {
        init {
            KafkaTestContainer.initialize("spring.elasticsearch.rest.uris")
        }

        @JvmField
        @RegisterExtension
        val kafka = KafkaTestContainer()
    }

    @Test
    fun test() {
    }
}
