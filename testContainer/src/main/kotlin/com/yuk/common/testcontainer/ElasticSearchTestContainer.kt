package com.yuk.common.testcontainer

import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.testcontainers.elasticsearch.ElasticsearchContainer
import org.testcontainers.utility.DockerImageName

open class ElasticSearchTestContainer : BeforeAllCallback {
    companion object {
        private var initialized: Boolean = false
        private var urlPropertyName: String = ""

        fun initialize(urlPropertyName: String) {
            if (initialized) return

            this.urlPropertyName = urlPropertyName

            initialized = true
        }

        private val es by lazy {
            if (initialized.not()) throw RuntimeException("you not set configuration. please call initialize() first")

            ElasticsearchContainer(DockerImageName.parse("docker.elastic.co/elasticsearch/elasticsearch-oss").withTag("7.10.2"))
        }
    }

    override fun beforeAll(context: ExtensionContext?) {
        es.start()

        configuration()
    }

    fun start() {
        if (es.isRunning.not())
            es.start()
    }

    fun stop() {
        if (es.isRunning)
            es.stop()
    }

    fun configuration() {
        System.setProperty(urlPropertyName, es.httpHostAddress)
    }
}
