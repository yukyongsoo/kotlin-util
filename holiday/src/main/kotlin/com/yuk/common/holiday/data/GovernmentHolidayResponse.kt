package com.yuk.common.holiday.data

internal data class GovernmentHolidayResponse(
    val response: Response
)

internal data class Response(
    val body: Body?,
    val header: Header
)

internal data class Header(
    val resultCode: String,
    val resultMsg: String
)

internal data class Body(
    val items: Items,
    val numOfRows: Int,
    val pageNo: Int,
    val totalCount: Int
)

internal data class Items(
    val item: List<Item>
)

internal data class Item(
    val dateKind: String,
    val dateName: String,
    val isHoliday: String,
    val locdate: Int,
    val seq: Int
)
