package com.yuk.common.querydsl

import com.querydsl.jpa.impl.JPAQueryFactory
import com.yuk.common.querydsl.base.QTestEntity
import com.yuk.common.querydsl.spring.SELECT
import com.yuk.common.testcontainer.MysqlTestConfiguration
import com.yuk.common.testcontainer.MysqlTestContainer
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class QuerydslTest {
    companion object {
        init {
            val config =
                MysqlTestConfiguration("spring.datasource", connectOption = "useUnicode=true&charset=utf8mb4&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&enabledTLSProtocols=TLSv1.2")

            MysqlTestContainer.initialize(listOf(config))
        }

        @RegisterExtension
        val mysql = MysqlTestContainer()
    }

    private val entity: QTestEntity = QTestEntity.testEntity

    @Autowired
    private lateinit var jpaQueryFactory: JPAQueryFactory

    @Test
    fun `기본 조회`() {
        val query = jpaQueryFactory SELECT entity FROM entity

        query.fetch()

        assert(
            query.toString() == """
                select testEntity
                from TestEntity testEntity
            """.trimIndent()
        )
    }

    @Test
    fun `EQUAL 조건문 테스트`() {
        val query = jpaQueryFactory SELECT entity FROM entity WHERE {
            entity.id EQUAL 1
        }

        query.fetch()

        assert(
            query.toString() == """
                select testEntity
                from TestEntity testEntity
                where testEntity.id = ?1
            """.trimIndent()
        )
    }
}
