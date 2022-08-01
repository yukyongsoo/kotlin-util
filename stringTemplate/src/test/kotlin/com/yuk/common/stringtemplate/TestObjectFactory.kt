package com.yuk.common.stringtemplate

import com.yuk.common.stringtemplate.group.Group
import com.yuk.common.stringtemplate.group.GroupId
import com.yuk.common.stringtemplate.template.Template
import com.yuk.common.stringtemplate.template.TemplateId

object TestObjectFactory {
    fun getGroup(): Group {
        return Group(GroupId("test"), "aaa")
    }

    fun getTemplate(): Template {
        return Template(TemplateId("test"), "asdfsdf {{1}}", TemplateId.NONE)
    }
}
