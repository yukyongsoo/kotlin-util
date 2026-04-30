package com.yuk.common.querydsl

import com.yuk.common.querydsl.base.TestRepositorySupport
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName

@Testcontainers
@SpringBootTest
class QuerydslSpringTest {
    companion object {
        @Container
        @ServiceConnection
        @JvmStatic
        val mysql = MySQLContainer(DockerImageName.parse("mysql:8.0.36"))
    }

    @Autowired
    private lateinit var testRepositorySupport: TestRepositorySupport

    @Test
    fun springTest() {
        testRepositorySupport.read()
    }
}
