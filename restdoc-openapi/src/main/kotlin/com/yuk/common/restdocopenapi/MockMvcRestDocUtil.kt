package com.yuk.common.restdocopenapi

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document
import com.epages.restdocs.apispec.ResourceDocumentation.resource
import com.epages.restdocs.apispec.ResourceSnippetParametersBuilder
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler

fun document(
    name: String,
    description: String,
    vararg documents: Documents
): RestDocumentationResultHandler {
    var builder = ResourceSnippetParametersBuilder().description(description)

    documents.forEach {
        it.init(builder)
        builder = it.build()
    }

    return document(
        name, description, false, resource(builder.build())
    )
}
