package com.yuk.common.stringtemplate.template.provider

import org.springframework.data.jpa.repository.JpaRepository

internal interface JPATemplateRepository : JpaRepository<TemplateEntity, String> {
    fun findAllByParentId(parentId: String): List<TemplateEntity>

    fun findAllByParentIdIsNull(): List<TemplateEntity>
}
