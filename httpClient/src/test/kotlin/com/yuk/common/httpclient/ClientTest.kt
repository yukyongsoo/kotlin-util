package com.yuk.common.httpclient

import org.junit.jupiter.api.Test
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono

class ClientTest {
    @Test
    fun getMethodTest() {
        val webclient = WebClient.builder().baseUrl("https://naver.com").build()

        ReactiveApiClient(webclient)
            .get(
                "",
            ).bodyToMono<String>()
            .block()
    }

    @Test
    fun postMethodTest() {
        val webclient = WebClient.builder().baseUrl("https://naver.com").build()

        ReactiveApiClient(webclient)
            .post(
                "",
                body = "null",
            ).bodyToMono<String>()
            .block()
    }

    @Test
    fun patchMethodTest() {
        val webclient = WebClient.builder().baseUrl("https://naver.com").build()

        ReactiveApiClient(webclient)
            .patch(
                "",
                body = "null",
            ).bodyToMono<String>()
            .block()
    }
}
