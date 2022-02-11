package com.yuk.common.testcontainer

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension

class ElasticSearchTestSample {
    companion object {
        init {
            ElasticSearchTestContainer.initialize("spring.elasticsearch.rest.uris")
        }

        @JvmField
        @RegisterExtension
        val es = ElasticSearchTestContainer()
    }

    @Test
    fun test() {
    }
}
