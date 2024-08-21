package com.yuk.common.stringtemplate.template.provider

import com.yuk.common.stringtemplate.template.Template
import com.yuk.common.stringtemplate.template.TemplateId

interface TemplateProvider {
    fun getOrNull(id: TemplateId): Template?

    fun getByIds(ids: Collection<TemplateId>): List<Template>

    fun getRoots(): List<Template>

    fun create(
        id: TemplateId,
        content: String,
        parentId: TemplateId? = null,
    )

    fun updateContent(
        id: TemplateId,
        content: String,
    )

    fun getChild(parentId: TemplateId): List<Template>

    fun attachChild(
        parentId: TemplateId,
        childId: TemplateId,
    )

    fun detachChild(
        parentId: TemplateId,
        childId: TemplateId,
    )
}
