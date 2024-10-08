package com.yuk.common.stringtemplate.parser

sealed class Part

data class KeywordPart(
    val key: String,
    val value: String,
) : Part()

data class StringPart(
    val value: String,
) : Part()
