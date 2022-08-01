package com.yuk.common.stringtemplate.template

import com.yuk.common.stringtemplate.AllOpen
import com.yuk.common.stringtemplate.parser.Parser
import com.yuk.common.stringtemplate.parser.SubstitutionResult
import com.yuk.common.stringtemplate.template.provider.TemplateProvider

@AllOpen
class TemplateManager(
    private val templateProvider: TemplateProvider
) {
    private val parser = Parser()

    fun get(id: TemplateId): Template {
        val template = templateProvider.getOrNull(id)
            ?: throw IllegalArgumentException("Template not found: $id")

        return template
    }

    fun getByIds(ids: Collection<TemplateId>): List<Template> {
        return templateProvider.getByIds(ids)
    }

    fun getRoots(): List<Template> {
        return templateProvider.getRoots()
    }

    fun getChild(parentId: TemplateId): List<Template> {
        return templateProvider.getChild(parentId)
    }

    fun create(id: TemplateId, content: String, parentId: TemplateId? = null) {
        templateProvider.create(id, content, parentId)
    }

    fun attachChild(parentId: TemplateId, childId: TemplateId) {
        templateProvider.attachChild(parentId, childId)
    }

    fun detachChild(parentId: TemplateId, childId: TemplateId) {
        templateProvider.detachChild(parentId, childId)
    }

    fun getResult(
        id: TemplateId,
        orderedData: List<String>
    ): SubstitutionResult {
        val template = get(id)

        return parser.getResult(template, orderedData)
    }
}
