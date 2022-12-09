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
            .expectBody<TestController.NestedTestResponseBody<List<TestController.NestedTestResponseBody<TestController.TestResponseBody>>>>()
            .consumeWith {
                document(it) {
                    name = ""
                    description = ""
                    request {
                        header {
                            string("X-PLATFORM", "플랫폼 증명값")
                        }
                        path {
                            number("id", description = "id")
                        }
                        query {
                            string("param", description = "param")
                        }
                        body {
                            objects {
                                number("categoryId", "문제 식별자")
                                string("content", "버그 제보 내용")
                            }
                        }
                    }
                    response {
                        header {
                            string("x-header")
                        }
                        body {
                            objects {
                                string("code", "에러 코드", true)
                                string("message", "에러 메시지", true)
                                // TODO:: array and object combine
                                array("data") {
                                    objects("") {
                                        string("code", "에러 코드", true)
                                        string("message", "에러 메시지", true)
                                        objects("data") {
                                            stringArray("data")
                                            string("code", "에러 코드", true)
                                            string("message", "에러 메시지", true)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
    }
}
