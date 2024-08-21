package com.yuk.common.querydsl

import com.yuk.common.querydsl.base.TestRepositorySupport
import com.yuk.common.testcontainer.MysqlTestConfiguration
import com.yuk.common.testcontainer.MysqlTestContainer
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class QuerydslSpringTest {
    companion object {
        init {
            val config =
                MysqlTestConfiguration(
                    "spring.datasource",
                    connectOption = "useUnicode=true&charset=utf8mb4&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&enabledTLSProtocols=TLSv1.2",
                )

            MysqlTestContainer.initialize(listOf(config))
        }

        @RegisterExtension
        val mysql = MysqlTestContainer()
    }

    @Autowired
    private lateinit var testRepositorySupport: TestRepositorySupport

    @Test
    fun springTest() {
        testRepositorySupport.read()
    }
}
