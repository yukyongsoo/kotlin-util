package com.yuk.common.stringtemplate.group.provider

import org.springframework.data.jpa.repository.JpaRepository

internal interface MysqlGroupRepository: JpaRepository<GroupEntity,String>