package com.yuk.common.holiday.data

internal data class GovernmentMultiHolidayResponse(
    val response: MultiResponse
)

internal data class GovernmentSingleHolidayResponse(
    val response: SingleResponse
)

internal data class Header(
    val resultCode: String,
    val resultMsg: String
)

internal data class MultiResponse(
    val body: MultiBody?,
    val header: Header
)

internal data class MultiBody(
    val items: MultiItems?,
    val numOfRows: Int,
    val pageNo: Int,
    val totalCount: Int
)

internal data class MultiItems(
    val item: List<Item>
)

internal data class SingleResponse(
    val body: SingleBody?,
    val header: Header
)

internal data class SingleBody(
    val items: SingleItems?,
    val numOfRows: Int,
    val pageNo: Int,
    val totalCount: Int
)

internal data class SingleItems(
    val item: Item
)

internal data class Item(
    val dateKind: String,
    val dateName: String,
    val isHoliday: String,
    val locdate: Int,
    val seq: Int
)
