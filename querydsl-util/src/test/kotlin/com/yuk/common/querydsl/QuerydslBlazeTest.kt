package com.yuk.common.querydsl

import com.blazebit.persistence.CriteriaBuilderFactory
import com.blazebit.persistence.querydsl.BlazeJPAQuery
import com.yuk.common.querydsl.base.QTestEntity
import com.yuk.common.querydsl.base.TestEntity
import com.yuk.common.querydsl.blaze.SELECT
import com.yuk.common.testcontainer.MysqlTestConfiguration
import com.yuk.common.testcontainer.MysqlTestContainer
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import javax.persistence.EntityManager

@SpringBootTest
class QuerydslBlazeTest {
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
    private lateinit var criteriaBuilderFactory: CriteriaBuilderFactory

    @Autowired
    private lateinit var entityManager: EntityManager

    @Test
    fun blazeTest() {
        val blazeJPAQuery = BlazeJPAQuery<TestEntity>(entityManager, criteriaBuilderFactory)

        val jpqlQuery = blazeJPAQuery SELECT entity FROM entity WHERE {
            (entity.test EQUAL "a") AND (entity.id EQUAL 1)
        } ORDERBY entity.id.asc()

        jpqlQuery.fetchResults()
    }
}