package com.yuk.common.stringtemplate.group.provider

import com.yuk.common.stringtemplate.template.TemplateId
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "template_group")
internal class GroupEntity(
    @Id
    val id: String,
    @Column
    var descr: String,
) {
    @Column
    protected var templateIds: String = ""

    @javax.persistence.Transient
    private lateinit var _templateIdSet: MutableSet<TemplateId>
    val templateIdSet: Set<TemplateId>
        get() {
            checkInit()
            return _templateIdSet
        }

    fun attachTemplate(id: TemplateId) {
        checkInit()
        _templateIdSet.add(id)
        templateIds = templateIdSet.joinToString(",") { it.value }
    }

    fun detachTemplate(id: TemplateId) {
        checkInit()
        _templateIdSet.remove(id)
        templateIds = templateIdSet.joinToString(",") { it.value }
    }

    private fun checkInit() {
        if (::_templateIdSet.isInitialized.not()) {
            _templateIdSet =
                templateIds.split(",").filter(String::isNotBlank).mapTo(mutableSetOf()) { TemplateId(it) }
        }
    }
}
