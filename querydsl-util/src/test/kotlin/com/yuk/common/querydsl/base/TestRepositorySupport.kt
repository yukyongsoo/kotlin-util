package com.yuk.common.querydsl.base

import com.querydsl.core.QueryResults
import com.yuk.common.querydsl.AND
import com.yuk.common.querydsl.EQUAL
import com.yuk.common.querydsl.FROM
import com.yuk.common.querydsl.SELECT
import com.yuk.common.querydsl.WHERE
import com.yuk.common.querydsl.spring.SELECT
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Service

@Service
open class TestRepositorySupport : QuerydslRepositorySupport(TestEntity::class.java) {
    private val entity: QTestEntity = QTestEntity.testEntity

    fun read(): QueryResults<TestEntity> {
        val jpqlQuery =
            querydsl!! SELECT entity FROM entity WHERE {
                (entity.test EQUAL "a") AND (entity.id EQUAL 1)
            }

        // if you want pagination
        // pagingBy pageable

        // if you want sort,paging use below now
        // querydsl!!.applyPagination(pageable, jpqlQuery)

        return jpqlQuery.fetchResults()
    }
}
