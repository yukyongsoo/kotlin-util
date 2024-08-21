package com.yuk.common.openapidiff

import org.junit.jupiter.api.Test
import java.nio.file.Files
import kotlin.io.path.Path

class DiffTest {
    @Test
    fun webUrlTest() {
        val maker = OpenApiDiff()
        maker.diff(
            "http://api.product-inspection.stg-bunjang.co.kr/api-docs",
            "http://localhost:5000/api-docs",
        )

        assert(Files.exists(Path("NewApi.html")))
        assert(Files.exists(Path("NewApi.md")))
    }
}
