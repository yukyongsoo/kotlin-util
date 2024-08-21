package com.yuk.common.stringtemplate

import com.yuk.common.stringtemplate.group.Group
import com.yuk.common.stringtemplate.group.GroupId
import com.yuk.common.stringtemplate.parser.KeywordPart
import com.yuk.common.stringtemplate.parser.Part
import com.yuk.common.stringtemplate.parser.StringPart
import com.yuk.common.stringtemplate.template.Template
import com.yuk.common.stringtemplate.template.TemplateId

object TestObjectFactory {
    fun getGroup(): Group = Group(GroupId("test"), "aaa")

    fun getTemplate(): Template = Template(TemplateId("test"), "asdfsdf {{1}}", TemplateId.NONE)

    fun getPartList(): List<Part> =
        listOf(
            StringPart("asdfsdf "),
            KeywordPart("{{1}}", ""),
        )
}
