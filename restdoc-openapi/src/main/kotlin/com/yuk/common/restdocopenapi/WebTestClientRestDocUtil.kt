package com.yuk.common.restdocopenapi

import com.epages.restdocs.apispec.ResourceDocumentation.resource
import com.epages.restdocs.apispec.ResourceSnippetParametersBuilder
import com.epages.restdocs.apispec.WebTestClientRestDocumentationWrapper
import org.springframework.test.web.reactive.server.EntityExchangeResult
import java.util.function.Consumer

fun <T> webTestDocument(
    name: String,
    description: String,
    vararg documents: Documents
): Consumer<EntityExchangeResult<T>> {
    var builder = ResourceSnippetParametersBuilder().description(description)

    documents.forEach {
        it.init(builder)
        builder = it.build()
    }

    return WebTestClientRestDocumentationWrapper.document(
        name,
        description,
        false,
        resource(builder.build())
    )
}
