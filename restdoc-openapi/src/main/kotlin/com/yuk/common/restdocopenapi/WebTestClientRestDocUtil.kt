package com.yuk.common.restdocopenapi

import com.epages.restdocs.apispec.ResourceDocumentation.resource
import com.epages.restdocs.apispec.ResourceSnippetParametersBuilder
import com.epages.restdocs.apispec.WebTestClientRestDocumentationWrapper
import org.springframework.test.web.reactive.server.EntityExchangeResult

fun <T> webTestDocument(
    result: EntityExchangeResult<T>,
    name: String,
    description: String,
    vararg documents: Documents
) {
    var builder = ResourceSnippetParametersBuilder().description(description)

    documents.forEach {
        it.init(builder)
        builder = it.build()
    }

    return WebTestClientRestDocumentationWrapper.document<T>(
        name,
        description,
        false,
        resource(builder.build())
    ).accept(result)
}
