package com.yuk.common.restdocopenapi

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController {
    @GetMapping
    fun webfluxTest() {
    }

    @PostMapping
    fun mvcTest() {
    }
}
