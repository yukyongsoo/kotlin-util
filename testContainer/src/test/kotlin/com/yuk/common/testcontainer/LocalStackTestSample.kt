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
                    LocalStackContainer.Service.CLOUDWATCHLOGS,
                    LocalStackContainer.Service.KINESIS,
                    LocalStackContainer.Service.IAM
                )
            )
        }

        @JvmField
        @RegisterExtension
        val localstack = LocalStackTestContainer()
    }

    @Test
    fun test() {
        val sqs =
            localstack.getEndPoint(LocalStackContainer.Service.SQS)

        val kinesis =
            localstack.getEndPoint(LocalStackContainer.Service.KINESIS)

        val support = LocalStackSupport(sqs, kinesis)

        support.createQueue()
        support.createStream()

    }
}
