package com.yuk.common.stringtemplate.group.provider

import com.yuk.common.stringtemplate.template.TemplateId
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
internal class GroupEntity(
    @Id
    val id: String,
    @Column
    var descr: String,
) {
    @Column
    var templateIds: String = ""
        protected set

    @delegate:Transient
    private val templateIdList: MutableSet<TemplateId> by lazy {
        templateIds.split(",").filter(String::isNotBlank).mapTo(mutableSetOf()) { TemplateId(it) }
    }

    fun getTemplateIds(): Set<TemplateId> {
        return templateIdList
    }

    fun toTemplateGroup(list: List<TemplateId>) {
        templateIds = list.joinToString(",") { it.value }
    }

    fun attachTemplate(id: TemplateId) {
        templateIdList.add(id)
        templateIds = templateIdList.joinToString(",") { it.value }
    }

    fun detachTemplate(id: TemplateId) {
        templateIdList.remove(id)
        templateIds = templateIdList.joinToString(",") { it.value }
    }
}
