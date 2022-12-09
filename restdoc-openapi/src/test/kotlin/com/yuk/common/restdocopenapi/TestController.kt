package com.yuk.common.restdocopenapi

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController {
    @PatchMapping("/{id}")
    fun webfluxTest(
        @PathVariable id: Int,
        @RequestParam param: String,
        @RequestBody body: TestRequestBody
    ): ResponseEntity<NestedTestResponseBody<List<NestedTestResponseBody<TestResponseBody>>>> {
        val responseBody = NestedTestResponseBody(
            "message",
            "code",
            listOf<NestedTestResponseBody<TestResponseBody>>(
                NestedTestResponseBody(
                    "aaa",
                    "bbb",
                    TestResponseBody(
                        listOf(), "message", "code"
                    )
                )
            )
        )

        val response = ResponseEntity.ok()
            .header("x-header", "AAAA")
            .body(responseBody)

        return response
    }

    @PostMapping("/{id}")
    fun mvcTest(
        @PathVariable id: Int,
        @RequestParam param: String,
        @RequestBody body: TestRequestBody
    ): ResponseEntity<TestResponseBody> {
        val responseBody = TestResponseBody(listOf(), "message", "code")

        val response = ResponseEntity.ok()
            .header("x-header", "AAAA")
            .body(responseBody)

        return response
    }

    data class TestRequestBody(
        val categoryId: Int,
        val content: String
    )

    data class TestResponseBody(
        val data: List<String>,
        val message: String,
        val code: String
    )

    data class NestedTestResponseBody<T>(
        val message: String,
        val code: String,
        val data: T
    )
}
