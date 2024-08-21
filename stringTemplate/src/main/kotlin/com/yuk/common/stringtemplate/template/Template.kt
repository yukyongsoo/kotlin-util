package com.yuk.common.stringtemplate.template

data class Template(
    val id: TemplateId,
    val content: String,
    val parentId: TemplateId,
)
