package com.yuk.common.func.base

import org.springframework.stereotype.Service

@Service
class TestService2 {
    fun noArg() {
    }

    fun noReturn(string: String) {
    }

    fun a(string: String): String = "Test2.a"

    fun b(string: String) {
    }

    fun c(): String = "Test2.c"

    fun cc(): Int = 1
}
