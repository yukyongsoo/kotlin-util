package com.yuk.common.testcontainer

import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.kinesis.AmazonKinesisClientBuilder
import com.amazonaws.services.kinesis.model.CreateStreamRequest
import com.amazonaws.services.kinesis.model.GetRecordsRequest
import com.amazonaws.services.kinesis.model.GetShardIteratorRequest
import com.amazonaws.services.kinesis.model.PutRecordRequest
import com.amazonaws.services.kinesis.model.ShardIteratorType
import com.amazonaws.services.sqs.AmazonSQSClientBuilder
import com.amazonaws.services.sqs.model.CreateQueueRequest
import com.amazonaws.services.sqs.model.ReceiveMessageRequest
import com.amazonaws.services.sqs.model.SendMessageRequest
import java.nio.ByteBuffer

class LocalStackSupport(
    sqsEndpoint: AwsClientBuilder.EndpointConfiguration,
    kinesisEndpoint: AwsClientBuilder.EndpointConfiguration
) {
    private val sqs = AmazonSQSClientBuilder.standard()
        .withEndpointConfiguration(sqsEndpoint).build()

    private val kinesis = AmazonKinesisClientBuilder.standard()
        .withEndpointConfiguration(kinesisEndpoint).build()

    fun createQueue(name: String = "test-sqs") {
        val request = CreateQueueRequest(name)
            .addAttributesEntry("MessageRetentionPeriod", "86400")

        sqs.createQueue(request)
    }

    fun sendSqsMessage(queueName: String = "test-sqs", message: String) {
        val request = SendMessageRequest(
            sqs.getQueueUrl(queueName).queueUrl,
            message
        )

        sqs.sendMessage(request)
    }

    fun readSqsMessage(queueName: String = "test-sqs"): List<String> {
        val request = ReceiveMessageRequest(
            sqs.getQueueUrl(queueName).queueUrl
        )

        val result = sqs.receiveMessage(request)
        return result.messages.map { it.body }
    }

    fun createStream(name: String = "test-stream", sleepTime: Long = 1000) {
        val request = CreateStreamRequest()
            .withStreamName(name)
            .withShardCount(1)

        kinesis.createStream(request)

        // wait for mock created
        Thread.sleep(sleepTime)
    }

    fun sendKinesisMessage(streamName: String = "test-stream", data: String): String {
        val buffer = ByteBuffer.wrap(data.encodeToByteArray())

        val request = PutRecordRequest()
            .withPartitionKey("000")
            .withStreamName(streamName)
            .withData(buffer)

        val result = kinesis.putRecord(request)

        return result.sequenceNumber
    }

    fun readKinesisMessage(streamName: String = "test-stream", startingSequence: String): List<String> {
        val iterRequest = GetShardIteratorRequest()
            .withShardId("shardId-000000000000")
            .withStreamName(streamName)
            .withStartingSequenceNumber(startingSequence)
            .withShardIteratorType(ShardIteratorType.AT_SEQUENCE_NUMBER)

        val iterResult = kinesis.getShardIterator(iterRequest)

        val request = GetRecordsRequest()
            .withShardIterator(iterResult.shardIterator)

        val result = kinesis.getRecords(request)

        return result.records.map { Charsets.UTF_8.decode(it.data).toString() }
    }
}
