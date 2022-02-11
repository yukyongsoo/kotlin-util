package com.yuk.common.testcontainer

import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.testcontainers.containers.KafkaContainer
import org.testcontainers.utility.DockerImageName

class KafkaTestContainer : BeforeAllCallback {
    companion object {
        private var initialized: Boolean = false
        private var urlPropertyName: String = ""

        fun initialize(urlPropertyName: String) {
            if (initialized) return

            this.urlPropertyName = urlPropertyName

            initialized = true
        }

        private val kafka by lazy {
            if (initialized.not()) throw RuntimeException("you not set configuration. please call initialize() first")

            KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:6.2.1"))
        }
    }

    override fun beforeAll(context: ExtensionContext?) {
        kafka.start()

        configuration()
    }

    fun start() {
        if (kafka.isRunning.not()) kafka.start()
    }

    fun stop() {
        if (kafka.isRunning) kafka.stop()
    }

    fun configuration() {
        System.setProperty(urlPropertyName, kafka.bootstrapServers)
    }
}
