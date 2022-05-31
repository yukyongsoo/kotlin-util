package com.yuk.common.querydsl.base

import com.blazebit.persistence.querydsl.BlazeJPAQuery
import com.querydsl.core.QueryResults
import com.querydsl.jpa.impl.JPAQueryFactory
import com.yuk.common.querydsl.AND
import com.yuk.common.querydsl.EQUAL
import com.yuk.common.querydsl.FROM
import com.yuk.common.querydsl.OR
import com.yuk.common.querydsl.ORDERBY
import com.yuk.common.querydsl.SELECT
import com.yuk.common.querydsl.blaze.SELECT
import com.yuk.common.querydsl.WHERE
import com.yuk.common.querydsl.spring.SELECT
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Service

@Service
open class TestRepositorySupport(
    private val blazeJPAQuery: BlazeJPAQuery<TestEntity>,
    private val jpaQueryFactory: JPAQueryFactory
) : QuerydslRepositorySupport(TestEntity::class.java) {
    private val entity: QTestEntity = QTestEntity.testEntity

    fun read(): QueryResults<TestEntity> {
        val jpqlQuery = querydsl!! SELECT entity FROM entity WHERE {
            (entity.test EQUAL "a") AND (entity.id EQUAL 1)
        }

        // if you want pagination
        // pagingBy pageable

        // if you want sort use below now
        // querydsl!!.applyPagination(pageable, jpqlQuery)

        return jpqlQuery.fetchResults()
    }

    fun blazeRead(): QueryResults<TestEntity> {
        val jpqlQuery = blazeJPAQuery SELECT entity WHERE {
            (entity.test EQUAL "a") AND (entity.id EQUAL 1)
        } ORDERBY entity.id.asc()

        return jpqlQuery.fetchResults()
    }

    fun projectionRead(): QueryResults<TestProjectDto> {
        val test = jpaQueryFactory.SELECT<TestProjectDto> {
            arrayOf(entity.id, entity.test)
        } FROM entity WHERE {
            (entity.test EQUAL "a") AND (entity.id EQUAL 1) OR (entity.test EQUAL "a")
        }

        return test.fetchResults()
    }
}
