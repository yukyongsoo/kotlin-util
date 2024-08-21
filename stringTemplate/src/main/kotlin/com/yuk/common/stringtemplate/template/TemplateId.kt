package com.yuk.common.stringtemplate.template

data class TemplateId(
    val value: String,
) {
    companion object {
        val NONE = TemplateId("")
    }
}
