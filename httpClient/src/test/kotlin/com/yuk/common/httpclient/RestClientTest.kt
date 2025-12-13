package com.yuk.common.httpclient

import org.junit.jupiter.api.Test
import org.springframework.web.client.RestClient
import org.springframework.web.client.body

class RestClientTest {
    @Test
    fun getMethodTest() {
        val webclient = RestClient.builder().baseUrl("https://jsonplaceholder.typicode.com/posts/1").build()

        val result =
            RestApiClient(webclient)
                .get(
                    "",
                ).body<String>()

        println(result)
    }

    @Test
    fun postMethodTest() {
        val webclient = RestClient.builder().baseUrl("https://jsonplaceholder.typicode.com/posts").build()

        RestApiClient(webclient)
            .post(
                "",
                body = null,
            ).body<String>()
    }

    @Test
    fun patchMethodTest() {
        val webclient = RestClient.builder().baseUrl("https://jsonplaceholder.typicode.com/posts/1").build()

        RestApiClient(webclient)
            .patch(
                "",
                body = null,
            ).body<String>()
    }

    @Test
    fun putMethodTest() {
        val webclient = RestClient.builder().baseUrl("https://jsonplaceholder.typicode.com/posts/1").build()

        RestApiClient(webclient)
            .put(
                "",
                body = null,
            ).body<String>()
    }

    @Test
    fun deleteMethodTest() {
        val webclient = RestClient.builder().baseUrl("https://jsonplaceholder.typicode.com/posts/1").build()

        RestApiClient(webclient)
            .delete(
                "",
            )
    }
}
