package com.yuk.common.address

class SearchedAddress(
    val address: String,
    val oldAddress: String,
    val zipCode: String,
) {
    companion object {
        val EMPTY = SearchedAddress("", "", "")
    }
}
