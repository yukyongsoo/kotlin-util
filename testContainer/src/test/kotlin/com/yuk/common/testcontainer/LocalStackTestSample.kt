package com.yuk.common.testcontainer

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension
import org.testcontainers.containers.localstack.LocalStackContainer

class LocalStackTestSample {
    companion object {
        init {
            LocalStackTestContainer.initialize(
                setOf(
                    LocalStackContainer.Service.SQS,
                    LocalStackContainer.Service.DYNAMODB,
                    LocalStackContainer.Service.KINESIS
                )
            )
        }

        @JvmField
        @RegisterExtension
        val localstack = LocalStackTestContainer()
    }

    @Test
    fun test() {
        val endPoint =
            localstack.getEndPoint(LocalStackContainer.Service.SQS)
    }
}
