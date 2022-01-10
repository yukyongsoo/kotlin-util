package com.yuk.common.testcontainer

import com.amazonaws.client.builder.AwsClientBuilder
import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.testcontainers.containers.localstack.LocalStackContainer
import org.testcontainers.utility.DockerImageName

object LocalStackTestContainer : BeforeAllCallback {
    private var initialized: Boolean = false
    private var serviceList: Array<LocalStackContainer.Service> = arrayOf()

    fun initialize(serviceList: Set<LocalStackContainer.Service>) {
        LocalStackTestContainer.serviceList = serviceList.toTypedArray()
        initialized = true
    }

    private val localstack by lazy {
        if (initialized.not()) throw RuntimeException("you not set configuration. please call initialize() first")

        LocalStackContainer(DockerImageName.parse("localstack/localstack:0.11.3")).withServices(
            *serviceList
        )
    }

    fun getEndPoint(service: LocalStackContainer.Service): AwsClientBuilder.EndpointConfiguration {
        return localstack.getEndpointConfiguration(service)
            ?: throw RuntimeException("end point not found. did you register service?")
    }

    override fun beforeAll(context: ExtensionContext?) {
        if (localstack.isRunning.not()) localstack.start()
    }
}
