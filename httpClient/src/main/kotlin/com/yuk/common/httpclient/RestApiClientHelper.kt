package com.yuk.common.httpclient

import org.springframework.web.client.RestClient
import org.springframework.web.util.UriBuilder

internal object RestApiClientHelper {
    fun buildWithBody(
        spec: RestClient.RequestBodyUriSpec,
        path: String,
        pathVariables: List<Any>,
        queries: List<Pair<String, Any>>,
        headers: Map<String, String> = mapOf(),
        cookies: Map<String, String> = mapOf(),
    ): RestClient.RequestBodySpec = build(spec, path, pathVariables, queries, headers, cookies) as RestClient.RequestBodySpec

    fun build(
        spec: RestClient.RequestHeadersUriSpec<*>,
        path: String,
        pathVariables: List<Any>,
        queries: List<Pair<String, Any>>,
        headers: Map<String, String> = mapOf(),
        cookies: Map<String, String> = mapOf(),
    ): RestClient.RequestHeadersSpec<*> {
        val errorType =
            pathVariables.any {
                it !is String && it !is Number
            }

        if (errorType) {
            throw RuntimeException("pathVariables Only support string or number")
        }

        if (headers.isNotEmpty()) {
            headers.forEach { (key, value) ->
                spec.headers {
                    it.add(key, value)
                }
            }
        }

        if (cookies.isNotEmpty()) {
            cookies.forEach { (key, value) ->
                spec.cookies {
                    it.add(key, value)
                }
            }
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
}
