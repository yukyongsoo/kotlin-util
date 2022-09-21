package com.yuk.common.restdocopenapi

import com.epages.restdocs.apispec.HeaderDescriptorWithType
import com.epages.restdocs.apispec.ParameterDescriptorWithType
import com.epages.restdocs.apispec.ResourceDocumentation
import com.epages.restdocs.apispec.ResourceSnippetParametersBuilder
import com.epages.restdocs.apispec.SimpleType
import org.springframework.restdocs.payload.FieldDescriptor
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.restdocs.payload.PayloadDocumentation

abstract class Documents {
    protected lateinit var builder: ResourceSnippetParametersBuilder

    fun init(builder: ResourceSnippetParametersBuilder) {
        this.builder = builder
    }

    abstract fun build(): ResourceSnippetParametersBuilder
}
class Headers(private vararg val header: HeaderDescriptorWithType) : Documents() {
    override fun build(): ResourceSnippetParametersBuilder {
        return builder.requestHeaders(*header)
    }
}

class ResponseHeader(private vararg val header: HeaderDescriptorWithType) : Documents() {
    override fun build(): ResourceSnippetParametersBuilder {
        return builder.responseHeaders(*header)
    }
}

fun stringHeader(name: String, description: String = "", optional: Boolean = false): HeaderDescriptorWithType {
    return header(name, SimpleType.STRING, description, optional)
}

fun intHeader(name: String, description: String = "", optional: Boolean = false): HeaderDescriptorWithType {
    return header(name, SimpleType.INTEGER, description, optional)
}

fun boolHeader(name: String, description: String = "", optional: Boolean = false): HeaderDescriptorWithType {
    return header(name, SimpleType.BOOLEAN, description, optional)
}

private fun header(
    name: String,
    type: SimpleType,
    description: String = "",
    optional: Boolean = false
): HeaderDescriptorWithType {
    val header = ResourceDocumentation.headerWithName(name).description(description).type(type)
    return if (optional) header.optional() else header
}

class Paths(private vararg val path: ParameterDescriptorWithType) : Documents() {
    override fun build(): ResourceSnippetParametersBuilder {
        return builder.pathParameters(*path)
    }
}

fun stringPath(name: String, description: String = "", optional: Boolean = false): ParameterDescriptorWithType {
    return path(name, SimpleType.STRING, description, optional)
}

fun integerPath(name: String, description: String = "", optional: Boolean = false): ParameterDescriptorWithType {
    return path(name, SimpleType.INTEGER, description, optional)
}

fun boolPath(name: String, description: String = "", optional: Boolean = false): ParameterDescriptorWithType {
    return path(name, SimpleType.BOOLEAN, description, optional)
}

private fun path(
    name: String,
    type: SimpleType,
    description: String = "",
    optional: Boolean = false
): ParameterDescriptorWithType {
    val path = ResourceDocumentation.parameterWithName(name).description(description).type(type)
    return if (optional) path.optional() else path
}

class Params(private vararg val parameter: ParameterDescriptorWithType) : Documents() {
    override fun build(): ResourceSnippetParametersBuilder {
        return builder.requestParameters(*parameter)
    }
}

fun stringParam(name: String, description: String = "", optional: Boolean = false): ParameterDescriptorWithType {
    return param(name, SimpleType.STRING, description, optional)
}

fun intParam(name: String, description: String = "", optional: Boolean = false): ParameterDescriptorWithType {
    return param(name, SimpleType.INTEGER, description, optional)
}

fun boolParam(name: String, description: String = "", optional: Boolean = false): ParameterDescriptorWithType {
    return param(name, SimpleType.BOOLEAN, description, optional)
}

private fun param(name: String, type: SimpleType, description: String, optional: Boolean): ParameterDescriptorWithType {
    val param = ResourceDocumentation.parameterWithName(name).type(type).description(description)
    return if (optional) param.optional() else param
}

class RequestBody(private val section: Section) : Documents() {
    override fun build(): ResourceSnippetParametersBuilder {
        return builder.requestFields(section.build())
    }
}

class ResponseBody(private val section: Section) : Documents() {
    override fun build(): ResourceSnippetParametersBuilder {
        return builder.responseFields(section.build())
    }
}

interface Component {
    fun build(): List<FieldDescriptor>
    fun setParent(name: String)
}

abstract class Section(
    private val name: String = "",
    private val type: JsonFieldType,
    private val description: String = "",
    private val optional: Boolean = false,
    private vararg val component: Component
) : Component {
    private var parent: String = ""

    override fun build(): List<FieldDescriptor> {
        val list = mutableListOf<FieldDescriptor>()

        if (parent.isNotBlank() && name.isNotBlank()) {
            var section = PayloadDocumentation.subsectionWithPath("$parent.$name")
                .type(type).description(description)
            section = if (optional) section.optional() else section
            list.add(section)
        }

        component.forEach {
            if (parent.isNotBlank() && name.isNotBlank())
                it.setParent("$parent.$name")
            else if (parent.isNotBlank())
                it.setParent(parent)
            else if (name.isNotBlank())
                it.setParent(name)

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
    vararg components: Component
) : Section("$name[]", JsonFieldType.ARRAY, description, optional, *components) {
    constructor(vararg components: Component) : this("", "", false, *components)
}

class ObjectSection(
    name: String = "",
    description: String = "",
    optional: Boolean = false,
    vararg components: Component
) : Section(name, JsonFieldType.OBJECT, description, optional, *components) {
    constructor(vararg components: Component) : this("", "", false, *components)
}

abstract class Field(
    private val name: String,
    private val type: SimpleType,
    private val description: String = "",
    private val optional: Boolean = false
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
    optional: Boolean = false
) : Field(name, SimpleType.STRING, description, optional)

class NumberField(
    name: String,
    description: String = "",
    optional: Boolean = false
) : Field(name, SimpleType.NUMBER, description, optional)

class BooleanField(
    name: String,
    description: String = "",
    optional: Boolean = false
) : Field(name, SimpleType.BOOLEAN, description, optional)
