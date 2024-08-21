package com.yuk.common.stringtemplate.parser

import com.yuk.common.stringtemplate.template.TemplateId

data class SubstitutionResult(
    val templateId: TemplateId,
    val syntaxTree: List<Part>,
) {
    override fun toString(): String =
        syntaxTree.joinToString("") {
            when (it) {
                is KeywordPart -> it.value
                is StringPart -> it.value
            }
        }

    fun toTemplateString(): String =
        syntaxTree.joinToString("") {
            when (it) {
                is KeywordPart -> it.key
                is StringPart -> it.value
            }
        }
}
