package com.yuk.common.codesample.database

// import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource
// import org.springframework.transaction.support.TransactionSynchronizationManager
//
// class RoutingDataSource : AbstractRoutingDataSource() {
//    enum class DataSourceType {
//        WRITE,
//        READ
//    }
//
//    override fun determineCurrentLookupKey(): Any {
//        return if (TransactionSynchronizationManager.isCurrentTransactionReadOnly()) DataSourceType.READ
//        else DataSourceType.WRITE
//    }
// }
