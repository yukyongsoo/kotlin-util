package com.yuk.common.stringtemplate.template.provider

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "template")
internal class TemplateEntity(
    @Id
    val id: String,
    @Column
    val content: String
) {
    @Column
    var parentId: String? = null
}
