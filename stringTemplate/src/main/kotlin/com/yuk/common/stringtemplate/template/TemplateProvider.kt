package com.yuk.common.stringtemplate.template

interface TemplateProvider {
    fun getOrNull(id: TemplateId): Template?
    fun getByIds(ids: Collection<TemplateId>): List<Template>
    fun create(id: TemplateId, content: String)
    fun getChild(parentId: TemplateId): List<Template>
    fun attachChild(parentId: TemplateId, childId: TemplateId)
    fun detachChild(parentId: TemplateId, childId: TemplateId)
}
