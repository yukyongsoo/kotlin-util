package com.yuk.common.testcontainer

import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.kinesis.AmazonKinesisClientBuilder
import com.amazonaws.services.kinesis.model.CreateStreamRequest
import com.amazonaws.services.kinesis.model.StreamMode
import com.amazonaws.services.kinesis.model.StreamModeDetails
import com.amazonaws.services.sqs.AmazonSQSClientBuilder
import com.amazonaws.services.sqs.model.CreateQueueRequest


class LocalStackSupport(
    sqsEndpoint: AwsClientBuilder.EndpointConfiguration,
    kinesisEndpoint: AwsClientBuilder.EndpointConfiguration
) {
    private val sqs = AmazonSQSClientBuilder.standard()
        .withEndpointConfiguration(sqsEndpoint).build()

    private val kinesis = AmazonKinesisClientBuilder.standard()
        .withEndpointConfiguration(kinesisEndpoint).build()

    fun createQueue() {


        val request = CreateQueueRequest("test-sqs")
            .addAttributesEntry("DelaySeconds", "60")
            .addAttributesEntry("MessageRetentionPeriod", "86400")

        sqs.createQueue(request)
    }

    fun createStream() {
        val request = CreateStreamRequest().withStreamName("test-stream")
            .withShardCount(1)
            .withStreamModeDetails(StreamModeDetails().withStreamMode(StreamMode.ON_DEMAND))

        kinesis.createStream(request)
    }


}