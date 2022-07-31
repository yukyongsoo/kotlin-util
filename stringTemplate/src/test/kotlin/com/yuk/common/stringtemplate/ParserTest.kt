package com.yuk.common.stringtemplate

import com.yuk.common.stringtemplate.parser.Parser
import com.yuk.common.stringtemplate.template.Template
import com.yuk.common.stringtemplate.template.TemplateId
import org.junit.jupiter.api.RepeatedTest
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

    @RepeatedTest(1000)
    fun performance() {
        val template = Template(TemplateId("test"), "test {{keyword}} {{key word2}} {{key word3}} {{key word4}} {{key word5}} {{key word6}} test2", TemplateId.NONE)

        parser.getResult(template, listOf("A", "B", "C", "D", "E", "F"))
    }

    @RepeatedTest(1000)
    fun replacePerformance() {
        val content = "test {{keyword}} {{key word2}} {{key word3}} {{key word4}} {{key word5}} {{key word6}}    test2"

        content
            .replace("{{keyword}}", "A")
            .replace("{{key word2}}", "B")
            .replace("{{key word3}}", "C")
            .replace("{{key word4}}", "D")
            .replace("{{key word5}}", "E")
            .replace("{{key word6}}", "F")
    }
}
