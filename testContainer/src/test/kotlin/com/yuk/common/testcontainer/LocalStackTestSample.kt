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
                    LocalStackContainer.Service.IAM,
                ),
            )
        }

        @JvmField
        @RegisterExtension
        val localstack = LocalStackTestContainer()
    }

    private val sqs =
        localstack.getEndPoint(LocalStackContainer.Service.SQS)

    private val kinesis =
        localstack.getEndPoint(LocalStackContainer.Service.KINESIS)

    private val support = LocalStackSupport(sqs, kinesis)

    @Test
    fun createSqsQueue() {
        support.createQueue()
    }

    @Test
    fun sendSqsQueue() {
        createSqsQueue()

        support.sendSqsMessage(message = "asdfasdf")
    }

    @Test
    fun readSqsMessage() {
        sendSqsQueue()

        val list = support.readSqsMessage()

        assert(list.isNotEmpty())
    }

    @Test
    fun createKinesisStream() {
        support.createStream()
    }

    @Test
    fun sendKinesisStream() {
        createKinesisStream()

        support.sendKinesisMessage(data = "asdfasdfsdf")
    }

    @Test
    fun readKinesisMessage() {
        createKinesisStream()

        val seq = support.sendKinesisMessage(data = "asdfasdfsdf")

        val list = support.readKinesisMessage(startingSequence = seq)

        assert(list.isNotEmpty())
    }
}
