package com.yuk.common.stringtemplate.group.provider

import com.yuk.common.stringtemplate.group.Group
import com.yuk.common.stringtemplate.group.GroupId
import com.yuk.common.stringtemplate.template.TemplateId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull

class MysqlGroupProvider: GroupProvider {
    @Autowired
    private lateinit var defaultMysqlGroupRepository: MysqlGroupRepository

    override fun create(id: GroupId, descr: String) {
        val entity = GroupEntity(id.value, descr)

        defaultMysqlGroupRepository.save(entity)
    }

    override fun delete(id: GroupId) {
        val entity = defaultMysqlGroupRepository.findByIdOrNull(id.value)

        if (entity != null) {
            defaultMysqlGroupRepository.delete(entity)
        }
    }

    override fun updateDescription(id: GroupId, descr: String) {
        val entity = defaultMysqlGroupRepository.findByIdOrNull(id.value)

        entity?.descr = descr
    }

    override fun getAllExceptTemplateId(): List<Group> {
        val entities = defaultMysqlGroupRepository.findAll()

        return entities.mapNotNull(this::entityToDomain)
    }

    override fun getOrNull(id: GroupId): Group? {
        val entity = defaultMysqlGroupRepository.findByIdOrNull(id.value)

        return entityToDomain(entity)
    }

    override fun attachTemplate(id: GroupId, templateId: TemplateId) {
        val entity = defaultMysqlGroupRepository.findByIdOrNull(id.value)

        entity?.attachTemplate(templateId)
    }

    override fun detachTemplate(id: GroupId, templateId: TemplateId) {
        val entity = defaultMysqlGroupRepository.findByIdOrNull(id.value)

        entity?.detachTemplate(templateId)
    }

    private fun entityToDomain(entity: GroupEntity?): Group? {
        return entity?.let {
            Group(
                GroupId(it.id),
                it.descr
            ).apply {
                templateIdList = it.getTemplateIds()
            }
        }
    }
}