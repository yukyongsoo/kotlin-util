package com.yuk.common.stringtemplate.template

data class TemplateId(
    val id: String
) {
    companion object {
        val NONE = TemplateId("")
    }
}
