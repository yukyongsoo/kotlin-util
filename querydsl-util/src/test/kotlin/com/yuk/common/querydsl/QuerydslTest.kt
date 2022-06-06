package com.yuk.common.querydsl

import com.querydsl.jpa.impl.JPAQueryFactory
import com.yuk.common.querydsl.base.QTestEntity
import com.yuk.common.querydsl.base.QTestEntity2
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

    private val entity2: QTestEntity2 = QTestEntity2.testEntity2

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
    fun `조건문 테스트`() {
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

    @Test
    fun `중첩 조건문 테스트`() {
        val query = jpaQueryFactory SELECT entity FROM entity WHERE {
            entity.id EQUAL 1 AND ((entity.test EQUAL "name") OR (entity.test EQUAL "name2"))
        }

        query.fetch()

        assert(
            query.toString() == """
              select testEntity
              from TestEntity testEntity
              where testEntity.id = ?1 and (testEntity.test = ?2 or testEntity.test = ?3)
            """.trimIndent()
        )
    }

    @Test
    fun `조인 테스트`() {
        val query = jpaQueryFactory SELECT entity FROM entity JOIN
            entity2 ON (entity.id EQUAL entity2.id)

        query.fetch()

        assert(
            query.toString() == """
              select testEntity
              from TestEntity testEntity
                inner join TestEntity2 testEntity2 with testEntity.id = testEntity2.id
            """.trimIndent()
        )
    }

    @Test
    fun `Select Distinct 테스트`() {
        val query = jpaQueryFactory `SELECT DISTINCT` entity FROM entity JOIN
            entity2 ON (entity.id EQUAL entity2.id)

        query.fetch()

        assert(
            query.toString() == """
              select distinct testEntity
              from TestEntity testEntity
                inner join TestEntity2 testEntity2 with testEntity.id = testEntity2.id
            """.trimIndent()
        )
    }

    @Test
    fun `Left 조인 테스트`() {
        val query = jpaQueryFactory SELECT entity FROM entity LEFTJOIN
            entity2 ON (entity.id EQUAL entity2.id)

        query.fetch()

        assert(
            query.toString() == """
              select testEntity
              from TestEntity testEntity
                left join TestEntity2 testEntity2 with testEntity.id = testEntity2.id
            """.trimIndent()
        )
    }

    @Test
    fun `Right 조인 테스트`() {
        val query = jpaQueryFactory SELECT entity2 FROM entity2 RIGHTJOIN
            entity ON (entity2.id EQUAL entity.id)

        query.fetch()

        assert(
            query.toString() == """
              select testEntity2
              from TestEntity2 testEntity2
                right join TestEntity testEntity with testEntity2.id = testEntity.id
            """.trimIndent()
        )
    }

    @Test
    fun `페치조인 테스트`() {
        val query = jpaQueryFactory SELECT entity FROM entity FETCHJOIN
            entity2 ON (entity.id EQUAL entity2.id)

        query.fetch()

        assert(
            query.toString() == "select testEntity\nfrom TestEntity testEntity\n  inner join fetch TestEntity2 testEntity2 with testEntity.id = testEntity2.id"
        )
    }

}
