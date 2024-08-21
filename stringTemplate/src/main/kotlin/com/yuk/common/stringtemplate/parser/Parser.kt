package com.yuk.common.stringtemplate.parser

import com.yuk.common.stringtemplate.template.Template

class Parser {
    private val startSubstitutionKey = "{{"
    private val endSubstitutionKey = "}}"

    fun getResult(
        template: Template,
        orderedData: List<String>,
    ): SubstitutionResult {
        val tree = makeSyntaxTreeWithData(template.content, orderedData.toMutableList())

        return SubstitutionResult(template.id, tree)
    }

    fun makeSyntaxTree(content: String): List<Part> {
        val parts =
            parseTree(content) { keyword ->
                KeywordPart(keyword, "")
            }

        return parts
    }

    fun compareTree(
        currentTree: List<Part>,
        newTree: List<Part>,
    ) {
        val currentKeywords = currentTree.filterIsInstance<KeywordPart>()
        val newKeywords = newTree.filterIsInstance<KeywordPart>()

        if (currentKeywords.size != newKeywords.size) {
            throw IllegalArgumentException("keyword size is different")
        }

        for (i in currentKeywords.indices) {
            if (currentKeywords[i].key != newKeywords[i].key) {
                throw IllegalArgumentException("keyword is different")
            }
        }
    }

    private fun makeSyntaxTreeWithData(
        content: String,
        orderedData: MutableList<String>,
    ): List<Part> {
        val parts =
            parseTree(content) { keyword ->
                try {
                    val value = orderedData.removeAt(0)
                    KeywordPart(keyword, value)
                } catch (e: IndexOutOfBoundsException) {
                    throw IllegalArgumentException("치환을 위한 데이터가 부족합니다. ")
                }
            }

        if (orderedData.isNotEmpty()) {
            throw IllegalArgumentException("치환 데이터가 너무 많습니다. 남은 사이즈: ${orderedData.size}")
        }

        return parts
    }

    private inline fun parseTree(
        content: String,
        keywordFunc: (String) -> Part,
    ): List<Part> {
        val split = splitContext(content)

        val parts =
            split.map { keyword ->
                if (keyword.startsWith(startSubstitutionKey)) {
                    keywordFunc(keyword)
                } else {
                    StringPart(keyword)
                }
            }

        return parts
    }

    private fun splitContext(content: String): List<String> {
        val delimitWord =
            content.replace("\\{\\{[^}]*}}".toRegex()) {
                "***${it.value}***"
            }

        return delimitWord.split("***")
    }
}
