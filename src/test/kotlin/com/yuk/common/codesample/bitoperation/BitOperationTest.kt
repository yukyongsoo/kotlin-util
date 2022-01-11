package com.yuk.common.codesample.bitoperation

import org.junit.jupiter.api.Test

class BitOperationTest {
    @Test
    fun test() {
        val list = BitOperation.allOperation(7)

        assert(list.size == 3)
    }
}
