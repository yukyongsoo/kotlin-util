package com.yuk.common.querydsl.base

import org.springframework.data.jpa.repository.JpaRepository

interface TestRepository : JpaRepository<TestEntity, Int>
