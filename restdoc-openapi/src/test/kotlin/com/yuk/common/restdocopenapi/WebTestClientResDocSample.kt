package com.yuk.common.restdocopenapi

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient

@WebMvcTest
@AutoConfigureRestDocs
@AutoConfigureMockMvc(addFilters = false)
class WebTestClientResDocSample {
    @Autowired
    private lateinit var webTestClient: WebTestClient

    @Test
    fun test() {
        webTestClient.get()
            .uri("http://your-url", "1024")
            .exchange()
            .expectStatus().isOk
            .expectBody()
            .consumeWith(
                webTestDocument(
                    "sample", "desc",
                    Headers(
                        stringHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE),
                    ),
                    Paths(
                        stringPath("path", "description of the path parameter"),
                    ),
                    Params(
                        stringParam("queryParam", "description of the query parameter"),
                    ),
                    RequestBody(
                        ObjectSection(
                            NumberField("categoryId", "문제 식별자"),
                            StringField("content", "버그 제보 내용"),
                        ),
                    ),
                    ResponseBody(
                        ObjectSection(
                            NumberField("categoryId", "문제 식별자"),
                            StringField("content", "버그 제보 내용"),
                        ),
                    )
                )
            )
    }
}
