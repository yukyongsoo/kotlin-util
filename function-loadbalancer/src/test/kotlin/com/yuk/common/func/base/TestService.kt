package com.yuk.common.func.base

import com.yuk.common.func.DistributeCall
import org.springframework.stereotype.Service

@Service
class TestService {
    @DistributeCall("test", "noArg", TestService2::class, "test")
    fun noArg() {
    }

    @DistributeCall("test", "noReturn", TestService2::class, "test")
    fun noReturn(string: String) {
    }

    @DistributeCall("test", "a", NotBean::class, "test")
    fun notBeanCall(string: String): String = "Test.NotBeanCall"

    @DistributeCall("test", "sameClassCallSub", TestService::class, "test")
    fun sameClassCall(string: String): String = "Test.a"

    fun sameClassCallSub(string: String): String = "Test.aaa"

    @DistributeCall("test", "a", TestService2::class, "test")
    fun otherClassCall(string: String): String = "Test.aa"

    @DistributeCall("test", "c", TestService2::class, "test")
    fun differentArgs(string: String) {
    }

    @DistributeCall("test", "cc", TestService2::class, "test")
    fun differentReturn(): String = "Test.c"

    @DistributeCall("test", "sameClassCall", TestService::class, "test")
    fun recursiveCall(string: String) = "Test.a"

    @DistributeCall("test", "successRatioTestCallSub", TestService::class, "test2")
    fun successRatioTestCall(string: String): String = "Test.a"

    fun successRatioTestCallSub(string: String): String = "Test.aa"
}
