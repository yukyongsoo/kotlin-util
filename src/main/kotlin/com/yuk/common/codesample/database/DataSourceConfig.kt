package com.yuk.common.codesample.database

// import com.yuk.common.codesample.database.RoutingDataSource
// import com.zaxxer.hikari.HikariConfig
// import com.zaxxer.hikari.HikariDataSource
// import org.springframework.context.annotation.Bean
// import org.springframework.context.annotation.Configuration
// import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy
// import javax.sql.DataSource
//
// @Configuration
// class DataSourceConfig(
//    private val databaseProperty: DatabaseProperty
// ) {
//    @Bean
//    fun getDataSource() =
//        LazyConnectionDataSourceProxy(getRoutingDataSource())
//
//    private fun getRoutingDataSource(): DataSource {
//        val master = createDatasource(databaseProperty.url)
//        val readonly = databaseProperty.urlReadonly?.let { createDatasource(it) } ?: master
//
//        val datasourceMap = mutableMapOf<Any, Any>()
//        datasourceMap[RoutingDataSource.DataSourceType.WRITE] = master
//        datasourceMap[RoutingDataSource.DataSourceType.READ] = readonly
//
//        val routingDataSource = RoutingDataSource()
//        routingDataSource.setTargetDataSources(datasourceMap)
//        routingDataSource.setDefaultTargetDataSource(master)
//        routingDataSource.afterPropertiesSet()
//        return routingDataSource
//    }
//
//    private fun createDatasource(url: String): DataSource {
//        val config = HikariConfig()
//        config.maximumPoolSize = databaseProperty.poolsize ?: 10
//        config.driverClassName = databaseProperty.driverClassName
//        config.jdbcUrl = url
//        config.username = databaseProperty.username
//        config.password = databaseProperty.password
//
//        return HikariDataSource(config)
//    }
// }
