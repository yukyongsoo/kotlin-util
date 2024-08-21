package com.yuk.common.restdocopenapi

import com.epages.restdocs.apispec.SimpleType
import org.springframework.restdocs.payload.FieldDescriptor
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.restdocs.payload.PayloadDocumentation

interface Component {
    fun build(): List<FieldDescriptor>

    fun setParent(name: String)
}

abstract class Section(
    private val name: String = "",
    private val type: JsonFieldType,
    private val description: String = "",
    private val optional: Boolean = false,
    private vararg val component: Component,
) : Component {
    private var parent: String = ""

    override fun build(): List<FieldDescriptor> {
        val list = mutableListOf<FieldDescriptor>()

        if (name.isNotBlank()) {
            var section =
                PayloadDocumentation
                    .subsectionWithPath("$parent.$name")
                    .type(type)
                    .description(description)
            section = if (optional) section.optional() else section
            list.add(section)
        }

        component.forEach {
            if (parent.isNotBlank() && name.isNotBlank()) {
                it.setParent("$parent.$name")
            } else if (parent.isNotBlank()) {
                it.setParent(parent)
            } else if (name.isNotBlank()) {
                it.setParent(name)
            }

            list.addAll(it.build())
        }

        return list
    }

    override fun setParent(name: String) {
        parent = name.ifBlank { "" }
    }
}

class ArraySection(
    name: String = "",
    description: String = "",
    optional: Boolean = false,
    vararg components: Component,
) : Section("$name[]", JsonFieldType.ARRAY, description, optional, *components) {
    constructor(vararg components: Component) : this("", "", false, *components)
}

class ObjectSection(
    name: String = "",
    description: String = "",
    optional: Boolean = false,
    vararg components: Component,
) : Section(name, JsonFieldType.OBJECT, description, optional, *components) {
    constructor(vararg components: Component) : this("", "", false, *components)
}

abstract class Field(
    private val name: String,
    private val type: SimpleType,
    private val description: String = "",
    private val optional: Boolean = false,
) : Component {
    private var parent: String = ""

    override fun build(): List<FieldDescriptor> {
        val fieldName = if (parent.isNotBlank()) "$parent.$name" else name
        var field = PayloadDocumentation.fieldWithPath(fieldName).type(type).description(description)
        field = if (optional) field.optional() else field
        return listOf(field)
    }

    override fun setParent(name: String) {
        parent = name.ifBlank { "" }
    }
}

class StringField(
    name: String,
    description: String = "",
    optional: Boolean = false,
) : Field(name, SimpleType.STRING, description, optional)

class NumberField(
    name: String,
    description: String = "",
    optional: Boolean = false,
) : Field(name, SimpleType.NUMBER, description, optional)

class BooleanField(
    name: String,
    description: String = "",
    optional: Boolean = false,
) : Field(name, SimpleType.BOOLEAN, description, optional)
