package com.yuk.common.querydsl

import com.blazebit.persistence.CriteriaBuilderFactory
import com.blazebit.persistence.querydsl.BlazeJPAQuery
import com.yuk.common.querydsl.base.QTestEntity
import com.yuk.common.querydsl.base.TestEntity
import com.yuk.common.querydsl.blaze.SELECT
import jakarta.persistence.EntityManager
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.mysql.MySQLContainer
import org.testcontainers.utility.DockerImageName

@Testcontainers
@SpringBootTest
class QuerydslBlazeTest {
    companion object {
        @Container
        @ServiceConnection
        @JvmStatic
        val mysql = MySQLContainer(DockerImageName.parse("mysql"))
    }

    private val entity: QTestEntity = QTestEntity.testEntity

    @Autowired
    private lateinit var criteriaBuilderFactory: CriteriaBuilderFactory

    @Autowired
    private lateinit var entityManager: EntityManager

    @Test
    fun blazeTest() {
        val blazeJPAQuery = BlazeJPAQuery<TestEntity>(entityManager, criteriaBuilderFactory)

        val jpqlQuery =
            blazeJPAQuery SELECT entity FROM entity WHERE {
                (entity.test EQUAL "a") AND (entity.id EQUAL 1)
            } ORDERBY entity.id.asc()

        jpqlQuery.fetchResults()
    }
}
