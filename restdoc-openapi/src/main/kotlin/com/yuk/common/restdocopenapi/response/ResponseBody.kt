package com.yuk.common.restdocopenapi.response

import com.epages.restdocs.apispec.SimpleType
import com.yuk.common.restdocopenapi.JsonArray
import com.yuk.common.restdocopenapi.JsonObject
import org.springframework.restdocs.payload.FieldDescriptor
import org.springframework.restdocs.payload.PayloadDocumentation

@Response
class ResponseBody {
    val fields = mutableListOf<FieldDescriptor>()
    private var initialized = false

    fun objects(func: JsonObject.() -> Unit) {
        initializeCheck {
            val objects = JsonObject(fields, "")
            objects.func()
        }
    }

    fun array(func: JsonArray.() -> Unit) {
        initializeCheck {
            val array = JsonArray(fields, "")
            array.func()
        }
    }

    fun string(description: String) {
        initializeCheck {
            val field =
                PayloadDocumentation.fieldWithPath("").type(SimpleType.STRING).description(description)
            fields.add(field)
        }
    }

    fun number(description: String) {
        initializeCheck {
            val field =
                PayloadDocumentation.fieldWithPath("").type(SimpleType.NUMBER).description(description)
            fields.add(field)
        }
    }

    fun bool(description: String) {
        initializeCheck {
            val field =
                PayloadDocumentation.fieldWithPath("").type(SimpleType.BOOLEAN).description(description)
            fields.add(field)
        }
    }

    private fun initializeCheck(func: () -> Unit) {
        if (initialized)
            throw IllegalStateException("only one function use. string, number, bool, array, objects")
        func()
        initialized = true
    }
}
