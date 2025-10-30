package com.yuk.common.restdocopenapi.request

import com.epages.restdocs.apispec.ResourceSnippetParametersBuilder
import com.yuk.common.restdocopenapi.DocumentMark
import com.yuk.common.restdocopenapi.initOnce

@DslMarker
annotation class Request

@Request
@DocumentMark
class DocumentRequest(
    private var builder: ResourceSnippetParametersBuilder,
) {
    private var requestHeader: RequestHeader by initOnce()
    private var requestPath: RequestPath by initOnce()
    private var requestQuery: RequestQuery by initOnce()
    private var requestBody: RequestBody by initOnce()

    fun header(func: RequestHeader.() -> Unit) {
        val header = RequestHeader()
        header.func()
        builder = builder.requestHeaders(header.headers)
        this.requestHeader = header
    }

    fun path(func: RequestPath.() -> Unit) {
        val path = RequestPath()
        path.func()
        builder = builder.pathParameters(path.paths)
        this.requestPath = path
    }

    fun query(func: RequestQuery.() -> Unit) {
        val query = RequestQuery()
        query.func()
        builder = builder.queryParameters(query.queries)
        this.requestQuery = query
    }

    fun body(func: RequestBody.() -> Unit) {
        val body = RequestBody()
        body.func()
        builder = builder.requestFields(body.fields)
        this.requestBody = body
    }
}
