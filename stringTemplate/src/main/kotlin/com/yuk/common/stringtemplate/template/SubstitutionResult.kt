package com.yuk.common.stringtemplate.template

data class SubstitutionResult(
    val templateId: TemplateId,
    val syntaxTree: List<Part>
) {
    override fun toString(): String {
        return syntaxTree.joinToString("") {
            when (it) {
                is KeywordPart -> it.value
                is StringPart -> it.value
            }
        }
    }

    fun toTemplateString(): String {
        return syntaxTree.joinToString("") {
            when (it) {
                is KeywordPart -> it.key
                is StringPart -> it.value
            }
        }
    }
}
