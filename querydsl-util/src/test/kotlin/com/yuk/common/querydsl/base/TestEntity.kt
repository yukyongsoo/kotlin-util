package com.yuk.common.querydsl.base

import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "test")
class TestEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    @Column(name = "test", nullable = false)
    var test: String? = null

    @Enumerated(EnumType.STRING)
    @Column(name = "test2", nullable = false)
    var test2: TestEnum = TestEnum.A
}
