package com.yuk.common.restdocopenapi

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody

@WebFluxTest
@ExtendWith(SpringExtension::class)
@AutoConfigureRestDocs
class WebTestClientResDocSample {
    @Autowired
    private lateinit var webTestClient: WebTestClient

    @Test
    fun test() {
        val request = TestController.TestRequestBody(1, "111")

        webTestClient.patch()
            // Do not use SAM or direct build URI object
            .uri("/{id}?param={param}", 1, "aaa")
            .header("X-PLATFORM", "AAAAA")
            .bodyValue(request)
            .exchange()
            .expectStatus().isOk
            .expectBody<TestController.TestResponseBody>()
            .consumeWith {
                webTestDocument(
                    it,
                    name = "문제 버그 제보하기", description = "문제 버그 제보하기",
                    Headers(
                        stringHeader("X-PLATFORM", "플랫폼 증명값"),
                    ),
                    Paths(
                        integerPath("id", description = "id")
                    ),
                    Params(
                        stringParam("param", description = "param"),
                    ),
                    RequestBody(
                        ObjectSection(
                            NumberField("categoryId", "문제 식별자"),
                            StringField("content", "버그 제보 내용"),
                        ),
                    ),
                    ResponseHeader(
                        stringHeader("x-header"),
                    ),
                    ResponseBody(
                        ObjectSection(
                            ArraySection(
                                name = "data", description = "String"
                            ),
                            StringField("code", "에러 코드", true),
                            StringField("message", "에러 메시지", true),
                        ),
                    ),
                )
            }
    }
}
