package com.yuk.common.stringtemplate.template

class Parser {
    private val startSubstitutionKey = "{{"
    private val endSubstitutionKey = "}}"

    fun getResult(
        template: Template,
        orderedData: List<String>
    ): SubstitutionResult {
        val tree = makeSyntaxTree(template.content, orderedData.toMutableList())

        return SubstitutionResult(template.templateId, tree)
    }

    private fun makeSyntaxTree(content: String, orderedData: MutableList<String>): List<Part> {
        val split = splitContext(content)

        val parts = split.map { keyword ->
            if (keyword.startsWith(startSubstitutionKey)) {
                try {
                    val value = orderedData.removeAt(0)
                    KeywordPart(keyword, value)
                } catch (e: IndexOutOfBoundsException) {
                    throw IllegalArgumentException("치환을 위한 데이터가 부족합니다. ")
                }
            } else {
                StringPart(keyword)
            }
        }

        if (orderedData.isNotEmpty()) {
            throw IllegalArgumentException("치환 데이터가 너무 많습니다. 남은 사이즈: ${orderedData.size}")
        }

        return parts
    }

    private fun splitContext(content: String): List<String> {
        val delimitWord = content.replace("\\{\\{[^}]*}}".toRegex()) {
            "***${it.value}***"
        }

        return delimitWord.split("***")
    }
}
