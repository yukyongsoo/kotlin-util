package com.yuk.common.stringtemplate.group.provider

import com.yuk.common.stringtemplate.group.Group
import com.yuk.common.stringtemplate.group.GroupId
import com.yuk.common.stringtemplate.template.TemplateId

interface GroupProvider {
    fun create(id: GroupId, descr: String)
    fun delete(id: GroupId)
    fun updateDescription(id: GroupId, descr: String)
    fun getAllExceptTemplateId(): List<Group>
    fun getOrNull(id: GroupId): Group?
    fun attachTemplate(id: GroupId, templateId: TemplateId)
    fun detachTemplate(id: GroupId, templateId: TemplateId)
}
