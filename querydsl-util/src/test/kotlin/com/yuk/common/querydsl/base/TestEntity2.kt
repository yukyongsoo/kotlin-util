package com.yuk.common.querydsl.base

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "test2")
class TestEntity2 {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    @Column(name = "test2", nullable = false)
    var test2: String? = null
}
