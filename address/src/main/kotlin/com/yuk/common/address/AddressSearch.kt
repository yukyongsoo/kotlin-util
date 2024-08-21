package com.yuk.common.address

import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono

class AddressSearch(
    private val apiKey: String,
) {
    private val webClient =
        WebClient
            .builder()
            .baseUrl("https://www.juso.go.kr/addrlink/addrLinkApi.do")
            .build()
    private val specialCharRegexp = Regex("[!@#\\%\\$^&*]")
    private val preventWord =
        listOf(
            "OR",
            "SELECT",
            "INSERT",
            "DELETE",
            "UPDATE",
            "CREATE",
            "DROP",
            "EXEC",
            "UNION",
            "FETCH",
            "DECLARE",
            "TRUNCATE",
        )

    fun search(address: String): SearchedAddress {
        val list = searchByAPI(address)

        val firstAddress =
            list?.firstOrNull()
                ?: return SearchedAddress.EMPTY

        return firstAddress
    }

    fun searchAll(address: String): List<SearchedAddress> {
        val list = searchByAPI(address)

        return list ?: emptyList()
    }

    private fun searchByAPI(address: String): List<SearchedAddress>? {
        val filteredWord = filteringWord(address)

        val response =
            webClient
                .get()
                .uri {
                    it.queryParam("confmKey", apiKey)
                    it.queryParam("resultType", "json")
                    it.queryParam("keyword", filteredWord)
                    it.build()
                }.retrieve()
                .bodyToMono<GovernmentAddress>()
                .block()!!

        if (response.results.common.errorCode != "0") {
            throw RuntimeException(response.results.common.errorMessage)
        }

        val addressList = response.results.juso?.map { SearchedAddress(it.roadAddr, it.jibunAddr, it.zipNo) }

        return addressList
    }

    private fun filteringWord(address: String): String {
        var removed = address.replace(specialCharRegexp, "")

        preventWord.forEach {
            removed = removed.replace(it, "")
        }

        return removed
    }
}
