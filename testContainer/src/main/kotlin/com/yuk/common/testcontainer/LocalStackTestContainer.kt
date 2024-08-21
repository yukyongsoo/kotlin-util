package com.yuk.common.testcontainer

import com.amazonaws.client.builder.AwsClientBuilder
import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.testcontainers.containers.localstack.LocalStackContainer
import org.testcontainers.utility.DockerImageName

open class LocalStackTestContainer : BeforeAllCallback {
    companion object {
        private var initialized: Boolean = false
        private var serviceList: Array<LocalStackContainer.Service> = arrayOf()

        fun initialize(serviceList: Set<LocalStackContainer.Service>) {
            LocalStackTestContainer.serviceList = serviceList.toTypedArray()
            initialized = true
        }

        private val localstack by lazy {
            if (initialized.not()) throw RuntimeException("you not set configuration. please call initialize() first")

            LocalStackContainer(DockerImageName.parse("localstack/localstack:0.14.3")).withServices(
                *serviceList,
            )
        }
    }

    fun getEndPoint(service: LocalStackContainer.Service): AwsClientBuilder.EndpointConfiguration =
        localstack.getEndpointConfiguration(service)
            ?: throw RuntimeException("end point not found. did you register service?")

    fun start() {
        if (localstack.isRunning.not()) {
            localstack.start()
        }
    }

    fun stop() {
        if (localstack.isRunning) {
            localstack.stop()
        }
    }

    override fun beforeAll(context: ExtensionContext?) {
        start()
    }
}
