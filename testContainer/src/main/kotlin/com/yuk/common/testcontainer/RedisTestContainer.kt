package com.yuk.common.testcontainer

import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.testcontainers.containers.GenericContainer
import org.testcontainers.utility.DockerImageName

open class RedisTestContainer : BeforeAllCallback {
    companion object {
        private var initialized: Boolean = false
        private var hostPropertyList: List<String> = listOf()
        private var portPropertyList: List<String> = listOf()

        fun initialize(hostPropertyList: List<String>, portPropertyList: List<String>) {
            RedisTestContainer.hostPropertyList = hostPropertyList
            RedisTestContainer.portPropertyList = portPropertyList

            initialized = true
        }

        private val redis by lazy {
            if (initialized.not()) throw RuntimeException("you not set configuration. please call initialize() first")

            object :
                GenericContainer<Nothing>(DockerImageName.parse("redis:5.0.14")) {}.apply {
                withExposedPorts(6379)
            }
        }
    }

    fun start() {
        if (redis.isRunning.not()) redis.start()
    }

    fun stop() {
        if (redis.isRunning) redis.stop()
    }

    override fun beforeAll(context: ExtensionContext?) {
        start()

        hostPropertyList.forEach {
            System.setProperty(it, redis.containerIpAddress)
        }

        portPropertyList.forEach {
            System.setProperty(it, redis.firstMappedPort.toString())
        }
    }
}
