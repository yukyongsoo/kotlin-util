package com.yuk.common.testcontainer

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension

class MysqlTestSample {
    companion object {
        init {
            val config =
                MysqlTestConfiguration("spring.datasource", connectOption = "useUnicode=true&charset=utf8mb4&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&enabledTLSProtocols=TLSv1.2")

            MysqlTestContainer.initialize(listOf(config))
        }

        @RegisterExtension
        val mysql = MysqlTestContainer
    }

    @Test
    fun test() {
    }
}
