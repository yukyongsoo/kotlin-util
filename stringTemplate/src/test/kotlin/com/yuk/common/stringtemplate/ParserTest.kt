package com.yuk.common.stringtemplate

import com.yuk.common.stringtemplate.parser.KeywordPart
import com.yuk.common.stringtemplate.parser.Parser
import com.yuk.common.stringtemplate.template.Template
import com.yuk.common.stringtemplate.template.TemplateId
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ParserTest {
    private val parser = Parser()

    @Test
    fun errorOnOverflow() {
        val template = Template(TemplateId("test"), "test {{keyword}} test2", TemplateId.NONE)

        assertThrows<IllegalArgumentException> {
            parser.getResult(template, listOf("A", "B"))
        }
    }

    @Test
    fun errorOnUnderflow() {
        val template = Template(TemplateId("test"), "test {{keyword}} {{key word2}} test2", TemplateId.NONE)

        assertThrows<IllegalArgumentException> {
            parser.getResult(template, listOf("A"))
        }
    }

    @Test
    fun parse() {
        val template = Template(TemplateId("test"), "test {{key word}} test2", TemplateId.NONE)

        val result = parser.getResult(template, listOf("A"))

        assert(result.toString() == "test A test2")
    }

    @Test
    fun parseOnlyString() {
        val template = Template(TemplateId("test"), "test", TemplateId.NONE)

        val result = parser.getResult(template, listOf())

        assert(result.toString() == "test")
    }

    @Test
    fun parseOnlyKeyword() {
        val template = Template(TemplateId("test"), "{{keyword}}", TemplateId.NONE)

        val result = parser.getResult(template, listOf("A"))

        assert(result.toString() == "A")
    }

    @Test
    fun compareTree() {
        parser.compareTree(
            TestObjectFactory.getPartList(),
            TestObjectFactory.getPartList(),
        )
    }

    @Test
    fun compareTreeSizeError() {
        val newList =
            TestObjectFactory.getPartList().toMutableList().apply {
                add(KeywordPart("{{test}}", ""))
            }

        assertThrows<IllegalArgumentException> {
            parser.compareTree(
                TestObjectFactory.getPartList(),
                newList,
            )
        }
    }

    @Test
    fun compareTreeKeywordError() {
        val newList =
            TestObjectFactory.getPartList().toMutableList().apply {
                set(1, KeywordPart("{{test}}", ""))
            }

        assertThrows<IllegalArgumentException> {
            parser.compareTree(
                TestObjectFactory.getPartList(),
                newList,
            )
        }
    }
}
