package com.yuk.common.querydsl.base

import com.blazebit.persistence.Criteria
import com.blazebit.persistence.CriteriaBuilderFactory
import com.blazebit.persistence.querydsl.BlazeJPAQuery
import com.blazebit.persistence.spi.CriteriaBuilderConfiguration
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope
import org.springframework.context.annotation.ScopedProxyMode
import javax.persistence.EntityManager
import javax.persistence.EntityManagerFactory
import javax.persistence.PersistenceUnit

@Configuration
class TestConfig {
    @PersistenceUnit
    private lateinit var entityManagerFactory: EntityManagerFactory

    @Bean
    fun getCriteriaBuilderFactory(): CriteriaBuilderFactory {
        val config: CriteriaBuilderConfiguration = Criteria.getDefault()
        // do some configuration
        return config.createCriteriaBuilderFactory(entityManagerFactory)
    }

    @Bean
    @Scope("prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
    fun <T> getBlazeQuery(
        @Autowired entityManager: EntityManager
    ): BlazeJPAQuery<T> {
        return BlazeJPAQuery<T>(entityManager, getCriteriaBuilderFactory())
    }
}
