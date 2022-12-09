package com.yuk.common.restdocopenapi.request

import com.epages.restdocs.apispec.ParameterDescriptorWithType
import com.epages.restdocs.apispec.ResourceDocumentation
import com.epages.restdocs.apispec.SimpleType

@Request
class RequestPath {
    val paths = mutableListOf<ParameterDescriptorWithType>()

    fun string(name: String, description: String = "", optional: Boolean = false) {
        val path = path(
            name,
            SimpleType.STRING,
            description,
            optional
        )
        paths.add(path)
    }

    fun number(name: String, description: String = "", optional: Boolean = false) {
        val path = path(
            name,
            SimpleType.NUMBER,
            description,
            optional
        )
        paths.add(path)
    }

    fun bool(name: String, description: String = "", optional: Boolean = false) {
        val path = path(
            name,
            SimpleType.BOOLEAN,
            description,
            optional
        )
        paths.add(path)
    }

    private fun path(
        name: String,
        type: SimpleType,
        description: String,
        optional: Boolean
    ): ParameterDescriptorWithType {
        val path = ResourceDocumentation.parameterWithName(name).description(description).type(type)
        return if (optional) path.optional() else path
    }
}
