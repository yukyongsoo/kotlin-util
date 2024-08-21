package com.yuk.common.func.base

import com.yuk.common.func.WeightedFunction
import org.springframework.stereotype.Service

@Service
class TestService {
    @WeightedFunction(-1, "noArg", TestService2::class)
    fun noArg() {
    }

    @WeightedFunction(-1, "noReturn", TestService2::class)
    fun noReturn(string: String) {
    }

    @WeightedFunction(-1, "a", NotBean::class)
    fun notBeanCall(string: String): String = "Test.NotBeanCall"

    @WeightedFunction(-1, "aa", TestService::class)
    fun a(string: String): String = "Test.a"

    @WeightedFunction(-1, "a", TestService2::class)
    fun aa(string: String): String = "Test.aa"

    @WeightedFunction(-1, "c", TestService2::class)
    fun b(string: String) {
    }

    @WeightedFunction(-1, "cc", TestService2::class)
    fun c(): String = "Test.c"
}
