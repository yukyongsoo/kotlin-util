package com.yuk.common.restdocopenapi

import org.springframework.restdocs.payload.FieldDescriptor
import org.springframework.restdocs.payload.PayloadDocumentation

@DslMarker
annotation class BodyMarker

fun createPath(
    parent: String,
    mine: String,
): String =
    when {
        parent.isNotBlank() && mine.isNotBlank() -> "$parent.$mine"
        parent.isNotBlank() -> parent
        mine.isNotBlank() -> mine
        else -> ""
    }

fun fieldMaker(
    name: String,
    optional: Boolean,
    description: String,
    type: Any,
): FieldDescriptor =
    PayloadDocumentation
        .fieldWithPath(name)
        .type(type)
        .description(description)
        .run {
            if (optional) {
                this.optional()
            } else {
                this
            }
        }

fun sectionMaker(
    path: String,
    optional: Boolean,
    description: String,
    type: Any,
): FieldDescriptor =
    PayloadDocumentation
        .fieldWithPath(path)
        .type(type)
        .description(description)
        .run {
            if (optional) {
                optional()
            } else {
                this
            }
        }
