package com.yuk.common.httpclient

import org.springframework.http.HttpRequest
import org.springframework.http.HttpStatusCode
import org.springframework.http.client.ClientHttpResponse
import org.springframework.web.client.RestClient
import java.nio.charset.StandardCharsets

class RestApiClient(
    private val restClient: RestClient,
) {
    private val defaultErrorHandler: (HttpRequest, ClientHttpResponse) -> Unit = { httpRequest, clientResponse ->
        val response =
            String(
                clientResponse
                    .body
                    .readAllBytes(),
                StandardCharsets.UTF_8,
            )

        throw RuntimeException(response)
    }

    fun get(
        path: String,
        pathVariables: List<Any> = listOf(),
        queries: List<Pair<String, Any>> = listOf(),
        headers: Map<String, String> = mapOf(),
        cookies: Map<String, String> = mapOf(),
        errorHandler: (HttpRequest, ClientHttpResponse) -> Unit = defaultErrorHandler,
    ): RestClient.ResponseSpec {
        val spec =
            RestApiClientHelper.build(
                restClient.get(),
                path,
                pathVariables,
                queries,
                headers,
                cookies,
            )

        return send(spec, errorHandler)
    }

    fun <V> post(
        path: String,
        pathVariables: List<Any> = listOf(),
        queries: List<Pair<String, Any>> = listOf(),
        headers: Map<String, String> = mapOf(),
        cookies: Map<String, String> = mapOf(),
        body: V? = null,
        errorHandler: (HttpRequest, ClientHttpResponse) -> Unit = defaultErrorHandler,
    ): RestClient.ResponseSpec {
        val spec =
            RestApiClientHelper.buildWithBody(
                restClient.post(),
                path,
                pathVariables,
                queries,
                headers,
                cookies,
            )

        if (body != null) spec.body(body)

        return send(spec, errorHandler)
    }

    fun <V> put(
        path: String,
        pathVariables: List<Any> = listOf(),
        queries: List<Pair<String, Any>> = listOf(),
        headers: Map<String, String> = mapOf(),
        cookies: Map<String, String> = mapOf(),
        body: V? = null,
        errorHandler: (HttpRequest, ClientHttpResponse) -> Unit = defaultErrorHandler,
    ): RestClient.ResponseSpec {
        val spec =
            RestApiClientHelper.buildWithBody(
                restClient.put(),
                path,
                pathVariables,
                queries,
                headers,
                cookies,
            )

        if (body != null) spec.body(body)

        return send(spec, errorHandler)
    }

    fun <V> patch(
        path: String,
        pathVariables: List<Any> = listOf(),
        queries: List<Pair<String, Any>> = listOf(),
        headers: Map<String, String> = mapOf(),
        cookies: Map<String, String> = mapOf(),
        body: V? = null,
        errorHandler: (HttpRequest, ClientHttpResponse) -> Unit = defaultErrorHandler,
    ): RestClient.ResponseSpec {
        val spec =
            RestApiClientHelper.buildWithBody(
                restClient.patch(),
                path,
                pathVariables,
                queries,
                headers,
                cookies,
            )

        if (body != null) spec.body(body)

        return send(spec, errorHandler)
    }

    fun delete(
        path: String,
        pathVariables: List<Any> = listOf(),
        queries: List<Pair<String, Any>> = listOf(),
        headers: Map<String, String> = mapOf(),
        cookies: Map<String, String> = mapOf(),
        errorHandler: (HttpRequest, ClientHttpResponse) -> Unit = defaultErrorHandler,
    ): RestClient.ResponseSpec {
        val spec =
            RestApiClientHelper.build(
                restClient.delete(),
                path,
                pathVariables,
                queries,
                headers,
                cookies,
            )

        return send(spec, errorHandler)
    }

    private fun send(
        spec: RestClient.RequestHeadersSpec<*>,
        errorHandler: (HttpRequest, ClientHttpResponse) -> Unit,
    ): RestClient.ResponseSpec = spec.retrieve().onStatus(HttpStatusCode::isError, errorHandler)
}
