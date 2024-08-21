package com.yuk.common.address

import org.junit.jupiter.api.Test

class AddressSearchTest {
    private val search = AddressSearch("U01TX0FVVEgyMDIyMDEyODAyNDE1ODExMjE5MTI=")

    @Test
    fun `주소 검색`() {
        val address = search.search("서울 성동구 성수이로 22길 51")

        assert(address.zipCode == "04798")
        assert(address.address == "서울특별시 성동구 성수이로22길 51 (성수동2가)")
    }

    @Test
    fun `없는 주소 검색`() {
        val address = search.search("서울 성동구 성수이로 66666666")

        assert(address.zipCode == "")
        assert(address.address == "")
    }

    @Test
    fun `특수문자 필터링`() {
        val address = search.search("서울 성동구 성수이로 22길 51!!!!!!!@#$%@@#$@")

        assert(address.zipCode == "04798")
        assert(address.address == "서울특별시 성동구 성수이로22길 51 (성수동2가)")
    }

    @Test
    fun `금지어 필터링`() {
        val address = search.search("서울 성동구 성수이로 22길 51DELETEINSERT")

        assert(address.zipCode == "04798")
        assert(address.address == "서울특별시 성동구 성수이로22길 51 (성수동2가)")
    }
}
