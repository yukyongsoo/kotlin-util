package com.yuk.common.restdocopenapi.response

import com.epages.restdocs.apispec.ResourceSnippetParametersBuilder
import com.yuk.common.restdocopenapi.DocumentMark
import com.yuk.common.restdocopenapi.initOnce

@DslMarker
annotation class Response

@Response
@DocumentMark
open class DocumentResponse(
    private var builder: ResourceSnippetParametersBuilder,
) {
    private var responseHeader: ResponseHeader by initOnce()
    private var responseBody: ResponseBody by initOnce()

    fun header(func: ResponseHeader.() -> Unit) {
        val header = ResponseHeader()
        header.func()
        builder = builder.responseHeaders(header.headers)
        this.responseHeader = header
    }

    fun body(func: ResponseBody.() -> Unit) {
        val body = ResponseBody()
        body.func()
        builder = builder.responseFields(body.fields)
        this.responseBody = body
    }
}
