package com.yuk.common.restdocopenapi

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.request
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest
@ExtendWith(SpringExtension::class)
@AutoConfigureRestDocs
class MockMvcRestDocSample {
    @Autowired
    private lateinit var mockMvc: MockMvc

    val mapper = ObjectMapper()

    @Test
    fun test() {
        val request = mapper.writeValueAsString(
            TestController.TestRequestBody(1, "111")
        )

        mockMvc.perform(
            request(
                HttpMethod.POST, "/{id}", "1"
            )
                .content(request)
                .contentType(MediaType.APPLICATION_JSON)
                .header("X-PLATFORM", "AAAAA")
                .param("param", "aaa")
        )
            .andExpect(status().isOk)
            .andDo(
                document(
                    name = "asdf",
                    description = "asdf",
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
                                name = "data",
                                description = "String"
                            ),
                            StringField("code", "에러 코드", true),
                            StringField("message", "에러 메시지", true),
                        ),
                    )
                ),
            )
    }
}
