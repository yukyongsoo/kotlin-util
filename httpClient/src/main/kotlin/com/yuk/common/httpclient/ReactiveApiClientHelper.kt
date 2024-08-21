package com.yuk.common.httpclient

import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.util.UriBuilder

internal object ReactiveApiClientHelper {
    fun buildUriWithBody(
        spec: WebClient.RequestBodyUriSpec,
        path: String,
        pathVariables: List<Any>,
        queries: List<Pair<String, Any>>,
    ): WebClient.RequestBodySpec = buildUri(spec, path, pathVariables, queries) as WebClient.RequestBodySpec

    fun buildUri(
        spec: WebClient.RequestHeadersUriSpec<*>,
        path: String,
        pathVariables: List<Any>,
        queries: List<Pair<String, Any>>,
    ): WebClient.RequestHeadersSpec<*> {
        val errorType =
            pathVariables.any {
                it !is String && it !is Number
            }

        if (errorType) {
            throw RuntimeException("pathVariables Only support string or number")
        }

        return spec.uri { builder ->
            builder.path(path)
            buildQueries(builder, queries)
            builder.build(*pathVariables.toTypedArray())
        }
    }

    private fun buildQueries(
        uriBuilder: UriBuilder,
        queries: List<Pair<String, Any>>,
    ): UriBuilder {
        if (queries.isEmpty()) {
            return uriBuilder
        }

        val errorType =
            queries.any { (_, value) ->
                value !is String && value !is Number
            }

        if (errorType) {
            throw RuntimeException("query value Only support string or number")
        }

        queries.forEach { (key, value) ->
            uriBuilder.queryParam(key, value)
        }

        return uriBuilder
    }

    fun buildHeaderWithBody(
        spec: WebClient.RequestBodySpec,
        headers: Map<String, String>,
    ): WebClient.RequestBodySpec = buildHeader(spec, headers) as WebClient.RequestBodySpec

    fun buildHeader(
        spec: WebClient.RequestHeadersSpec<*>,
        headers: Map<String, String>,
    ): WebClient.RequestHeadersSpec<*> {
        var headerSpec = spec

        headers.forEach { (key, value) ->
            headerSpec = spec.header(key, value)
        }

        return headerSpec
    }
}
