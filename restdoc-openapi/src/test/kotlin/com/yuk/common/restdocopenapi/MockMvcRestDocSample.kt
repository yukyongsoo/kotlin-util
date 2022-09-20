package com.yuk.common.restdocopenapi

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.HttpMethod
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

    @Test
    fun test() {
        mockMvc.perform(
            request(
                HttpMethod.POST, "/"
            )
        )
            .andExpect(status().isOk)
            .andDo(
                document(
                    name = "문제 버그 제보하기",
                    description = "문제 버그 제보하기",
//                    Headers(
//                        stringHeader("X-PLATFORM", "플랫폼 증명값"),
//                    ),
//                    Paths(
//                        stringPath("a"),
//                        integerPath("a")
//                    ),
//                    Params(
//                        stringParam("asdfasdf"),
//                        stringParam("asdfasdf")
//                    ),
//                    RequestBody(
//                        ObjectSection(
//                            NumberField("categoryId", "문제 식별자"),
//                            StringField("content", "버그 제보 내용"),
//                        ),
//                    ),
//                    ResponseHeader(
//                        stringHeader("asdf"),
//                        intHeader("asdf")
//                    ),
//                    ResponseBody(
//                        ObjectSection(
//                            ArraySection(
//                                "asdf",
//                                components = arrayOf(StringField("code", "에러 코드", true))
//                            ),
//                            StringField("code", "에러 코드", true),
//                            StringField("message", "에러 메시지", true),
//                            StringField("data", "실제 데이터", true)
//                        ),
//                    )
                ),
            )
    }
}
