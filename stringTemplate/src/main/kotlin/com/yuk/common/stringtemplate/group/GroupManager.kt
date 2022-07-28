package com.yuk.common.stringtemplate.group

import com.yuk.common.stringtemplate.template.TemplateId
import com.yuk.common.stringtemplate.template.TemplateManager

class GroupManager(
    private val groupProvider: GroupProvider,
    private val templateManager: TemplateManager
) {
    fun create(id: GroupId, descr: String) {
    }

    fun updateDescription(id: GroupId, descr: String) {
    }

    fun attachTemplate(id: GroupId, templateId: TemplateId) {
    }

    fun detachTemplate(id: GroupId, templateId: TemplateId) {
    }

    fun getAllExceptTemplateId(): List<Group> {
        TODO()
    }

    fun get(id: GroupId): Group {
        TODO()
    }
}
