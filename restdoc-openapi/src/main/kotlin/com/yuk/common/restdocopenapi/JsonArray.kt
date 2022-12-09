package com.yuk.common.restdocopenapi

import com.epages.restdocs.apispec.SimpleType
import com.yuk.common.restdocopenapi.request.Request
import com.yuk.common.restdocopenapi.response.Response
import org.springframework.restdocs.payload.FieldDescriptor

@BodyMarker
@Request
@Response
class JsonArray(
    private val fields: MutableList<FieldDescriptor>,
    private val name: String
) {
    private var initialized = false

    fun number(fieldName: String, description: String = "", optional: Boolean = false) {
        initializeCheck {

            val path = createPath("$name[]", fieldName)

            val field =
                fieldMaker(path, optional, description, SimpleType.NUMBER)
            fields.add(field)
        }
    }

    fun string(fieldName: String, description: String = "", optional: Boolean = false) {
        initializeCheck {
            val path = createPath("$name[]", fieldName)

            val field =
                fieldMaker(path, optional, description, SimpleType.STRING)
            fields.add(field)
        }
    }

    fun bool(fieldName: String, description: String = "", optional: Boolean = false) {
        initializeCheck {
            val path = createPath("$name[]", fieldName)

            val field =
                fieldMaker(path, optional, description, SimpleType.BOOLEAN)
            fields.add(field)
        }
    }

    fun objects(
        fieldName: String,
        func: JsonObject.() -> Unit
    ) {
        initializeCheck {
            val path = createPath("$name[]", fieldName)

            val objects = JsonObject(fields, path)
            objects.func()
        }
    }

    private fun initializeCheck(func: () -> Unit) {
        if (initialized)
            throw IllegalStateException("array can have only one type")
        func()
        initialized = true
    }
}
