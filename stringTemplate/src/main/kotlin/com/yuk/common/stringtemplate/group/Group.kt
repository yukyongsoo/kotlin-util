package com.yuk.common.stringtemplate.group

import com.yuk.common.stringtemplate.template.TemplateId

data class Group(
    val id: GroupId,
    val descr: String,
) {
    var templateIdSet = setOf<TemplateId>()
}
