package com.yuk.common.httpclient

import org.junit.jupiter.api.Test
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono

class ReactiveClientTest {
    @Test
    fun getMethodTest() {
        val webclient = WebClient.builder().baseUrl("https://jsonplaceholder.typicode.com/posts/1").build()

        val result =
            ReactiveApiClient(webclient)
                .get(
                    "",
                ).bodyToMono<String>()
                .block()

        println(result)
    }

    @Test
    fun postMethodTest() {
        val webclient = WebClient.builder().baseUrl("https://jsonplaceholder.typicode.com/posts").build()

        ReactiveApiClient(webclient)
            .post(
                "",
                body = null,
            ).bodyToMono<String>()
            .block()
    }

    @Test
    fun patchMethodTest() {
        val webclient = WebClient.builder().baseUrl("https://jsonplaceholder.typicode.com/posts/1").build()

        ReactiveApiClient(webclient)
            .patch(
                "",
                body = null,
            ).bodyToMono<String>()
            .block()
    }

    @Test
    fun putMethodTest() {
        val webclient = WebClient.builder().baseUrl("https://jsonplaceholder.typicode.com/posts/1").build()

        ReactiveApiClient(webclient)
            .put(
                "",
                body = null,
            ).bodyToMono<String>()
            .block()
    }

    @Test
    fun deleteMethodTest() {
        val webclient = WebClient.builder().baseUrl("https://jsonplaceholder.typicode.com/posts/1").build()

        ReactiveApiClient(webclient)
            .delete(
                "",
            )
    }
}
