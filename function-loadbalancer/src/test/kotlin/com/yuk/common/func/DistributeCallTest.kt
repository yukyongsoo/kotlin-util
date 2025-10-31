package com.yuk.common.func

import com.yuk.common.func.base.TestService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class DistributeCallTest {
    @Autowired
    private lateinit var testService: TestService

    companion object {
        @BeforeAll
        @JvmStatic
        fun init() {
            DistributorRatioManager.add("test", FixedDistributorRatio(70))
            DistributorRatioManager.add(
                "test2",
                SuccessRatedDistributorRatio(
                    70,
                    100,
                    10,
                    5,
                ),
            )
        }
    }

    @AfterEach
    fun clean() {
        DistributorManager.remove("test")
        DistributorRatioManager.add(
            "test2",
            SuccessRatedDistributorRatio(
                70,
                100,
                10,
                5,
            ),
        )
    }

    @Test
    fun `같은 클래스의 밸런싱`() {
        testService.sameClassCall("test same class balancing")
    }

    @Test
    fun `다른 클래스의 밸런싱`() {
        testService.otherClassCall("")
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
            testService.differentArgs("")
        }
    }

    @Test
    fun `리턴형이 다른 경우 에러`() {
        assertThrows<IllegalArgumentException> {
            testService.differentReturn()
        }
    }

    @Test
    fun `aspect 순환 참조 시 에러`() {
        assertThrows<IllegalArgumentException> {
            testService.recursiveCall("")
        }
    }

    @RepeatedTest(1000)
    fun `콜 분배 테스트`() {
        val returnList = mutableListOf<String>()

        for (i in 1..10) {
            val result = testService.sameClassCall("test same class balancing")
            returnList.add(result)
        }

        assertThat(returnList.count { it == "Test.a" }).isLessThanOrEqualTo(7)
        assertThat(returnList.count { it == "Test.aaa" }).isLessThanOrEqualTo(3)
        assertThat(returnList.size).isEqualTo(10)
    }

    @RepeatedTest(1000)
    fun `성공 비율 근거 분배 테스트`() {
        val returnList = mutableListOf<String>()

        for (i in 1..100) {
            val result = testService.successRatioTestCall("test same class balancing")
            returnList.add(result)
        }

        assertThat(returnList.count { it == "Test.a" }).isLessThanOrEqualTo(10)
        assertThat(returnList.count { it == "Test.aa" }).isLessThanOrEqualTo(95)
        assertThat(returnList.size).isEqualTo(100)
    }

    @RepeatedTest(1000)
    fun `함수형 콜 분배 테스트`() {
        DistributorRatioManager.add("test", FixedDistributorRatio(70))

        val returnList = mutableListOf<String>()

        for (i in 1..10) {
            val result =
                distribute(
                    "test",
                    { testService.sameClassCallSub("test same class balancing") },
                    { testService.successRatioTestCallSub("test same class balancing") },
                )
            returnList.add(result)
        }

        assertThat(returnList.count { it == "Test.aaa" }).isLessThanOrEqualTo(7)
        assertThat(returnList.count { it == "Test.aa" }).isLessThanOrEqualTo(3)
        assertThat(returnList.size).isEqualTo(10)
    }

    @Test
    fun `시간에 따른 primary 선택 비율 감소 테스트`() {
        val ratio =
            TimeBasedDistributorRatio(
                primaryCallPercent = 80,
                secondaryIncreaseRatio = 20,
                durationMiles = 1_000,
            )

        val (primarySelected, secondarySelected) = runTimeBasedDistributorRatio(ratio)

        ratio.run()

        val (primarySelected2, secondarySelected2) = runTimeBasedDistributorRatio(ratio)

        ratio.run()

        val (primarySelected3, secondarySelected3) = runTimeBasedDistributorRatio(ratio)

        assertTrue(secondarySelected + primarySelected == 100)
        assertTrue(secondarySelected2 + primarySelected2 == 100)
        assertTrue(secondarySelected3 + primarySelected3 == 100)

        assertTrue(secondarySelected < secondarySelected2)
        assertTrue(secondarySelected < secondarySelected3)
        assertTrue(secondarySelected2 < secondarySelected3)
    }

    private fun runTimeBasedDistributorRatio(ratio: TimeBasedDistributorRatio): Pair<Int, Int> {
        var primarySelected = 0
        var secondarySelected = 0

        repeat(100) {
            val f =
                ratio.determine(
                    primaryFunc = { primarySelected++ },
                    secondaryFunc = { secondarySelected++ },
                )
            f()
        }

        return primarySelected to secondarySelected
    }
}
