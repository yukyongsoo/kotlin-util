package com.yuk.common.httpclient

import org.springframework.http.HttpStatus
import org.springframework.web.reactive.function.client.ClientResponse
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import reactor.core.publisher.Mono

class ReactiveApiClient(
    private val webClient: WebClient,
) {
    private val defaultErrorHandler: (ClientResponse) -> Mono<out Throwable> = { clientResponse ->
        clientResponse
            .bodyToMono<String>()
            .flatMap { Mono.error(RuntimeException(it)) }
    }

    fun get(
        path: String,
        pathVariables: List<Any> = listOf(),
        queries: List<Pair<String, Any>> = listOf(),
        headers: Map<String, String> = mapOf(),
        errorHandler: (ClientResponse) -> Mono<out Throwable> = defaultErrorHandler,
    ): WebClient.ResponseSpec {
        var spec =
            ReactiveApiClientHelper.buildUri(
                webClient.get(),
                path,
                pathVariables,
                queries,
            )

        if (headers.isNotEmpty()) {
            spec =
                ReactiveApiClientHelper.buildHeader(spec, headers)
        }

        return send(spec, errorHandler)
    }

    fun delete(
        path: String,
        pathVariables: List<Any> = listOf(),
        queries: List<Pair<String, Any>> = listOf(),
        headers: Map<String, String> = mapOf(),
        errorHandler: (ClientResponse) -> Mono<out Throwable> = defaultErrorHandler,
    ): WebClient.ResponseSpec {
        var spec =
            ReactiveApiClientHelper.buildUri(
                webClient.delete(),
                path,
                pathVariables,
                queries,
            )

        if (headers.isNotEmpty()) {
            spec =
                ReactiveApiClientHelper.buildHeader(spec, headers)
        }

        return send(spec, errorHandler)
    }

    fun <V> post(
        path: String,
        pathVariables: List<Any> = listOf(),
        queries: List<Pair<String, Any>> = listOf(),
        headers: Map<String, String> = mapOf(),
        body: V? = null,
        errorHandler: (ClientResponse) -> Mono<out Throwable> = defaultErrorHandler,
    ): WebClient.ResponseSpec {
        var spec =
            ReactiveApiClientHelper.buildUriWithBody(
                webClient.post(),
                path,
                pathVariables,
                queries,
            )

        if (headers.isNotEmpty()) {
            spec =
                ReactiveApiClientHelper.buildHeaderWithBody(spec, headers)
        }

        if (body != null) spec.bodyValue(body)

        return send(spec, errorHandler)
    }

    fun <V> put(
        path: String,
        pathVariables: List<Any> = listOf(),
        queries: List<Pair<String, Any>> = listOf(),
        headers: Map<String, String> = mapOf(),
        body: V? = null,
        errorHandler: (ClientResponse) -> Mono<out Throwable> = defaultErrorHandler,
    ): WebClient.ResponseSpec {
        var spec =
            ReactiveApiClientHelper.buildUriWithBody(
                webClient.put(),
                path,
                pathVariables,
                queries,
            )

        if (headers.isNotEmpty()) {
            spec =
                ReactiveApiClientHelper.buildHeaderWithBody(spec, headers)
        }

        if (body != null) spec.bodyValue(body)

        return send(spec, errorHandler)
    }

    fun <V> patch(
        path: String,
        pathVariables: List<Any> = listOf(),
        queries: List<Pair<String, Any>> = listOf(),
        headers: Map<String, String> = mapOf(),
        body: V? = null,
        errorHandler: (ClientResponse) -> Mono<out Throwable> = defaultErrorHandler,
    ): WebClient.ResponseSpec {
        var spec =
            ReactiveApiClientHelper.buildUriWithBody(
                webClient.patch(),
                path,
                pathVariables,
                queries,
            )

        if (headers.isNotEmpty()) {
            spec =
                ReactiveApiClientHelper.buildHeaderWithBody(spec, headers)
        }

        if (body != null) spec.bodyValue(body)

        return send(spec, errorHandler)
    }

    private fun send(
        spec: WebClient.RequestHeadersSpec<*>,
        errorHandler: (ClientResponse) -> Mono<out Throwable>,
    ): WebClient.ResponseSpec = spec.retrieve().onStatus(HttpStatus::isError, errorHandler)
}
