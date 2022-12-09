package com.yuk.common.restdocopenapi

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper
import com.epages.restdocs.apispec.ResourceDocumentation
import com.epages.restdocs.apispec.ResourceSnippetParametersBuilder
import com.epages.restdocs.apispec.WebTestClientRestDocumentationWrapper
import com.yuk.common.restdocopenapi.request.DocumentRequest
import com.yuk.common.restdocopenapi.response.DocumentResponse
import org.springframework.test.web.reactive.server.EntityExchangeResult
import org.springframework.test.web.servlet.MvcResult

@DslMarker
annotation class DocumentMark
inline fun <reified T> document(
    result: T,
    func: Document.() -> Unit
): Document {
    val document = Document()
    document.func()

    when (result) {
        is EntityExchangeResult<*> -> document.build(result)
        is MvcResult -> document.build(result)
        else -> throw IllegalStateException("result is type of EntityExchangeResult or MvcResult")
    }

    return document
}

@DocumentMark
class Document {
    var name: String by initOnce()
    var description: String by initOnce()
    private var request: DocumentRequest by initOnce()
    private var response: DocumentResponse by initOnce()

    private val builder = ResourceSnippetParametersBuilder().description("")

    fun build(result: MvcResult) {
        return MockMvcRestDocumentationWrapper.document(
            name,
            description,
            false,
            ResourceDocumentation.resource(builder.build())
        ).handle(result)
    }

    fun <T> build(result: EntityExchangeResult<T>) {
        WebTestClientRestDocumentationWrapper.document<T>(
            name,
            description,
            false,
            ResourceDocumentation.resource(builder.build())
        ).accept(result)
    }

    fun request(func: DocumentRequest.() -> Unit) {
        val request = DocumentRequest(builder)
        request.func()
        this.request = request
    }

    fun response(func: DocumentResponse.() -> Unit) {
        val response = DocumentResponse(builder)
        response.func()
        this.response = response
    }
}
