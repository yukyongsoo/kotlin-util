package com.yuk.common.stringtemplate.template.provider

import com.yuk.common.stringtemplate.AllOpen
import com.yuk.common.stringtemplate.template.Template
import com.yuk.common.stringtemplate.template.TemplateId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull

@AllOpen
class JPATemplateProvider : TemplateProvider {
    @Autowired
    private lateinit var templateRepository: JPATemplateRepository

    override fun getOrNull(id: TemplateId): Template? {
        val entity = templateRepository.findByIdOrNull(id.value)

        return entityToDomain(entity)
    }

    override fun getByIds(ids: Collection<TemplateId>): List<Template> =
        templateRepository
            .findAllById(ids.map { it.value })
            .mapNotNull(this::entityToDomain)

    override fun getRoots(): List<Template> =
        templateRepository
            .findAllByParentIdIsNull()
            .mapNotNull(this::entityToDomain)

    override fun create(
        id: TemplateId,
        content: String,
        parentId: TemplateId?,
    ) {
        val entity =
            TemplateEntity(
                id.value,
                content,
            )

        entity.parentId = parentId?.value

        templateRepository.save(entity)
    }

    override fun updateContent(
        id: TemplateId,
        content: String,
    ) {
        templateRepository.findByIdOrNull(id.value)?.let {
            it.content = content
        }
    }

    override fun getChild(parentId: TemplateId): List<Template> =
        templateRepository
            .findAllByParentId(parentId.value)
            .mapNotNull(this::entityToDomain)

    override fun attachChild(
        parentId: TemplateId,
        childId: TemplateId,
    ) {
        templateRepository.findByIdOrNull(childId.value)?.let {
            it.parentId = parentId.value
        }
    }

    override fun detachChild(
        parentId: TemplateId,
        childId: TemplateId,
    ) {
        templateRepository.findByIdOrNull(childId.value)?.let {
            it.parentId = null
        }
    }

    private fun entityToDomain(entity: TemplateEntity?): Template? =
        entity?.let { template ->
            Template(
                TemplateId(template.id),
                template.content,
                template.parentId?.let { TemplateId(it) } ?: TemplateId.NONE,
            )
        }
}
