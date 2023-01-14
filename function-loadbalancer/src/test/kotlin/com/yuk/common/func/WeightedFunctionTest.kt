package com.yuk.common.func

import com.yuk.common.func.base.TestService
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class WeightedFunctionTest {
    @Autowired
    private lateinit var testService: TestService

    @Test
    fun `같은 클래스의 밸런싱`() {
        testService.a("test same class balancing")
    }

    @Test
    fun `다른 클래스의 밸런싱`() {
        testService.aa("")
    }

    @Test
    fun `인자가 없는 메서드 밸런싱`() {
        testService.noArg()
    }

    @Test
    fun `리턴이 없는 메서드 밸런싱`() {
        testService.noReturn("")
    }

    @Test
    fun `인자가 다른 경우 에러`() {
        assertThrows<IllegalArgumentException> {
            testService.b("")
        }
    }

    @Test
    fun `리턴형이 다른 경우 에러`() {
        assertThrows<IllegalArgumentException> {
            testService.c()
        }
    }

    @Test
    fun `스프링 빈이 아닌 경우 에러`() {
        assertThrows<IllegalArgumentException> {
            testService.notBeanCall("aa")
        }
    }
}
