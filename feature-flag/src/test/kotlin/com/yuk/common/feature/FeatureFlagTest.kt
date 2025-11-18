package com.yuk.common.feature

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.LocalDateTime

class FeatureFlagTest {
    @Test
    fun featureFlagWithResultTrueOnewayTest() {
        val strategy = BooleanFlagStrategy { true }

        val str =
            featureFlagWithResult(
                strategy,
                { "test" },
                { throw IllegalStateException() },
            )

        assertThat(str).isEqualTo("test")
    }

    @Test
    fun featureFlagWithResultFalseOnewayTest() {
        val strategy = BooleanFlagStrategy { false }

        val str =
            featureFlagWithResult(
                strategy,
                { "test" },
                { null },
            )

        assertThat(str).isNull()
    }

    @Test
    fun featureFlagTrueOnewayTest() {
        val strategy = BooleanFlagStrategy { true }

        var str = "test"
        featureFlag(
            strategy,
            { str = "A" },
        )

        assertThat(str).isEqualTo("A")
    }

    @Test
    fun featureFlagFalseOnewayTest() {
        val strategy = BooleanFlagStrategy { false }

        var str = "test"
        featureFlag(
            strategy,
            { "test" },
        )

        assertThat(str).isEqualTo("test")
    }

    @Test
    fun featureFlagEnabledCheckTest() {
        var testFlag = true

        FeatureFlagStrategyFactory.putBooleans("boolean") { testFlag }

        assertThat("boolean".featureEnabled()).isTrue

        testFlag = false

        assertThat("boolean".featureEnabled()).isFalse
    }

    val featureFlagWithResultTestFunction: (key: String) -> String? = {
        it.featureFlagWithResult(
            { "B" },
            { "A" },
        )
    }

    var str = "test"
    val featureFlagTestFunction: (key: String) -> Unit = {
        it.featureFlag(
            { str = "B" },
            { str = "A" },
        )
    }

    @Nested
    inner class BooleanTest {
        var testFlag = true
        val key = "boolean"

        @BeforeEach
        fun init() {
            FeatureFlagStrategyFactory.putBooleans(key) { testFlag }
        }

        @AfterEach
        fun cleanup() {
            testFlag = true
        }

        @Test
        fun featureFlagWithResultTest() {
            val str = featureFlagWithResultTestFunction(key)

            assertThat(str).isEqualTo("B")

            testFlag = false

            val str2 = featureFlagWithResultTestFunction(key)

            assertThat(str2).isEqualTo("A")
        }

        @Test
        fun featureFlagTest() {
            featureFlagTestFunction(key)

            assertThat(str).isEqualTo("B")

            testFlag = false

            featureFlagTestFunction(key)

            assertThat(str).isEqualTo("A")
        }
    }

    @Nested
    inner class DateRangeTest {
        var start = LocalDate.now()
        var end = LocalDate.now().plusDays(1)
        val key = "date"

        @BeforeEach
        fun init() {
            FeatureFlagStrategyFactory.putDateRange(
                key,
                { start },
                { end },
            )
        }

        @AfterEach
        fun cleanup() {
            start = LocalDate.now()
            end = LocalDate.now().plusDays(1)
        }

        @Test
        fun featureFlagWithResultTest() {
            val str = featureFlagWithResultTestFunction(key)

            assertThat(str).isEqualTo("B")

            start = LocalDate.now().plusDays(1)
            end = LocalDate.now().plusDays(2)

            val str2 = featureFlagWithResultTestFunction(key)

            assertThat(str2).isEqualTo("A")
        }

        @Test
        fun featureFlagTest() {
            featureFlagTestFunction(key)

            assertThat(str).isEqualTo("B")

            start = LocalDate.now().plusDays(1)
            end = LocalDate.now().plusDays(2)

            featureFlagTestFunction(key)

            assertThat(str).isEqualTo("A")
        }
    }

    @Nested
    inner class DateTimeRangeTest {
        var start = LocalDateTime.now()
        var end = LocalDateTime.now().plusDays(1)
        val key = "dateTime"

        @BeforeEach
        fun init() {
            FeatureFlagStrategyFactory.putDateTimeRange(
                key,
                { start },
                { end },
            )
        }

        @AfterEach
        fun cleanup() {
            start = LocalDateTime.now()
            end = LocalDateTime.now().plusDays(1)
        }

        @Test
        fun featureFlagWithResultTest() {
            val str = featureFlagWithResultTestFunction(key)

            assertThat(str).isEqualTo("B")

            start = LocalDateTime.now().plusDays(1)
            end = LocalDateTime.now().plusDays(2)

            val str2 = featureFlagWithResultTestFunction(key)

            assertThat(str2).isEqualTo("A")
        }

        @Test
        fun featureFlagTest() {
            featureFlagTestFunction(key)

            assertThat(str).isEqualTo("B")

            start = LocalDateTime.now().plusDays(1)
            end = LocalDateTime.now().plusDays(2)

            featureFlagTestFunction(key)

            assertThat(str).isEqualTo("A")
        }
    }
}
