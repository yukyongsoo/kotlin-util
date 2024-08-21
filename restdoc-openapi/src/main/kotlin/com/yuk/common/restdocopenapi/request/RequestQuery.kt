package com.yuk.common.restdocopenapi.request

import com.epages.restdocs.apispec.ParameterDescriptorWithType
import com.epages.restdocs.apispec.ResourceDocumentation
import com.epages.restdocs.apispec.SimpleType

@Request
class RequestQuery {
    val queries = mutableListOf<ParameterDescriptorWithType>()

    fun string(
        name: String,
        description: String = "",
        optional: Boolean = false,
    ): ParameterDescriptorWithType = param(name, SimpleType.STRING, description, optional)

    fun number(
        name: String,
        description: String = "",
        optional: Boolean = false,
    ): ParameterDescriptorWithType = param(name, SimpleType.NUMBER, description, optional)

    fun bool(
        name: String,
        description: String = "",
        optional: Boolean = false,
    ): ParameterDescriptorWithType = param(name, SimpleType.BOOLEAN, description, optional)

    private fun param(
        name: String,
        type: SimpleType,
        description: String,
        optional: Boolean,
    ): ParameterDescriptorWithType {
        val param = ResourceDocumentation.parameterWithName(name).type(type).description(description)
        return if (optional) param.optional() else param
    }
}
