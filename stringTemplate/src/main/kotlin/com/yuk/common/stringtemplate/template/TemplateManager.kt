package com.yuk.common.stringtemplate.template

class TemplateManager(
    private val templateProvider: TemplateProvider
) {
    private val parser = Parser()

    fun get(id: TemplateId): Template {
        TODO()
    }

    fun getByIds(ids: Collection<TemplateId>): List<Template> {
        TODO()
    }

    fun getRoots(): List<Template> {
        TODO()
    }

    fun getChild(id: TemplateId): List<Template> {
        TODO()
    }

    fun create(id: TemplateId, content: String) {
    }

    fun attachChild(parentId: TemplateId, childId: TemplateId) {
    }

    fun detachChild(parentId: TemplateId, childId: TemplateId) {
    }

    fun getResult(
        id: TemplateId,
        orderedData: List<String>
    ): SubstitutionResult {
        val template = get(id)

        return parser.getResult(template, orderedData)
    }
}
