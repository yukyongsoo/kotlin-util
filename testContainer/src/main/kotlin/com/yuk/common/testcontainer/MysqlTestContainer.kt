package com.yuk.common.testcontainer

import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.utility.DockerImageName

open class MysqlTestContainer : BeforeAllCallback {
    companion object {
        private var initialized: Boolean = false
        private var initSqlPath: String = ""
        private var configurationList: List<MysqlTestConfiguration> = listOf()

        fun initialize(
            configurationList: List<MysqlTestConfiguration>,
            initSql: String = "",
        ) {
            MysqlTestContainer.configurationList = configurationList
            initSqlPath = initSql

            initialized = true
        }

        private val mysql by lazy {
            if (initialized.not()) throw RuntimeException("you not set configuration. please call initialize() first")

            MySQLContainer<Nothing>(DockerImageName.parse("mysql:5.7.36")).apply {
                withDatabaseName("test")
                withUsername("root")
                withPassword("")
                withExposedPorts(3306)

                if (initSqlPath.isNotBlank()) {
                    withInitScript(initSqlPath)
                }
            }
        }
    }

    fun start() {
        if (mysql.isRunning.not()) mysql.start()
    }

    fun stop() {
        if (mysql.isRunning) mysql.stop()
    }

    override fun beforeAll(context: ExtensionContext?) {
        if (mysql.isRunning.not()) mysql.start()

        configurationList.forEach { configuration ->
            val url =
                if (configuration.connectOption.isNotBlank()) {
                    "jdbc:mysql://${mysql.containerIpAddress}:${mysql.firstMappedPort}/${configuration.databaseName}?${configuration.connectOption}"
                } else {
                    "jdbc:mysql://${mysql.containerIpAddress}:${mysql.firstMappedPort}/${configuration.databaseName}"
                }

            System.setProperty("${configuration.rootPropertyName}.driver-class-name", "com.mysql.cj.jdbc.Driver")
            System.setProperty("${configuration.rootPropertyName}.url", url)
            System.setProperty("${configuration.rootPropertyName}.username", "root")
            System.setProperty("${configuration.rootPropertyName}.password", "")
        }
    }
}

class MysqlTestConfiguration(
    rootPropertyName: String,
    val databaseName: String = "test",
    connectOption: String = "",
) {
    var rootPropertyName: String
        private set
    var connectOption: String
        private set

    init {
        if (rootPropertyName.last() == '.') {
            this.rootPropertyName = rootPropertyName.dropLast(1)
        } else {
            this.rootPropertyName = rootPropertyName
        }

        if (connectOption.first() == '?') {
            this.connectOption = connectOption.drop(1)
        } else {
            this.connectOption = connectOption
        }
    }
}
