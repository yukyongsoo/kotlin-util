package com.yuk.common.restdocopenapi.request

import com.epages.restdocs.apispec.HeaderDescriptorWithType
import com.epages.restdocs.apispec.ResourceDocumentation
import com.epages.restdocs.apispec.SimpleType

@Request
class RequestHeader {
    val headers = mutableListOf<HeaderDescriptorWithType>()

    fun string(name: String, description: String = "", optional: Boolean = false) {
        val header = header(name, SimpleType.STRING, description, optional)
        headers.add(header)
    }

    fun number(name: String, description: String = "", optional: Boolean = false) {
        val header = header(name, SimpleType.NUMBER, description, optional)
        headers.add(header)
    }

    fun bool(name: String, description: String = "", optional: Boolean = false) {
        val header = header(name, SimpleType.BOOLEAN, description, optional)
        headers.add(header)
    }

    private fun header(
        name: String,
        type: SimpleType,
        description: String,
        optional: Boolean
    ): HeaderDescriptorWithType {
        val header = ResourceDocumentation.headerWithName(name).description(description).type(type)
        return if (optional) header.optional() else header
    }
}
