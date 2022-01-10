package com.yuk.common.querydsl.base

import com.querydsl.core.QueryResults
import com.querydsl.jpa.JPQLQuery
import com.yuk.common.querydsl.and
import com.yuk.common.querydsl.equal
import com.yuk.common.querydsl.select
import com.yuk.common.querydsl.where
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Service

@Service
open class TestRepositorySupport : QuerydslRepositorySupport(TestEntity::class.java) {
    private val entity: QTestEntity = QTestEntity.testEntity

    fun read(): QueryResults<TestEntity> {
        val jpqlQuery: JPQLQuery<TestEntity> = querydsl select entity where {
            (entity.test equal "a") and (entity.id equal 1)
        }
        // if you want pagination
        // pagingBy pageable

        // if you want sort use below now
        // querydsl!!.applyPagination(pageable, jpqlQuery)

        return jpqlQuery.fetchResults()
    }
}
