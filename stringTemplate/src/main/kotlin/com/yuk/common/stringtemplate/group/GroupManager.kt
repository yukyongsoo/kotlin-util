package com.yuk.common.stringtemplate.group

import com.yuk.common.stringtemplate.AllOpen
import com.yuk.common.stringtemplate.group.provider.GroupProvider
import com.yuk.common.stringtemplate.template.TemplateId
import com.yuk.common.stringtemplate.template.TemplateManager

@AllOpen
class GroupManager(
    private val groupProvider: GroupProvider,
    private val templateManager: TemplateManager
) {
    fun create(id: GroupId, descr: String) {
        groupProvider.create(id, descr)
    }

    fun updateDescription(id: GroupId, descr: String) {
        groupProvider.updateDescription(id, descr)
    }

    fun attachTemplate(id: GroupId, templateId: TemplateId) {
        templateManager.get(templateId)

        groupProvider.attachTemplate(id, templateId)
    }

    fun detachTemplate(id: GroupId, templateId: TemplateId) {
        templateManager.get(templateId)

        groupProvider.detachTemplate(id, templateId)
    }

    fun getAllExceptTemplateId(): List<Group> {
        return groupProvider.getAllExceptTemplateId()
    }

    fun get(id: GroupId): Group {
        return groupProvider.getOrNull(id)
            ?: throw IllegalArgumentException("Group not found: $id")
    }
}
