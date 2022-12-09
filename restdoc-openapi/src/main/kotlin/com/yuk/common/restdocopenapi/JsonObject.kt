package com.yuk.common.restdocopenapi

import com.epages.restdocs.apispec.SimpleType
import com.yuk.common.restdocopenapi.request.Request
import com.yuk.common.restdocopenapi.response.Response
import org.springframework.restdocs.payload.FieldDescriptor
import org.springframework.restdocs.payload.JsonFieldType

@BodyMarker
@Request
@Response
class JsonObject(
    private val fields: MutableList<FieldDescriptor>,
    private val name: String
) {
    fun number(fieldName: String, description: String = "", optional: Boolean = false) {
        val path = createPath(name, fieldName)

        val field =
            fieldMaker(path, optional, description, SimpleType.NUMBER)
        fields.add(field)
    }

    fun string(fieldName: String, description: String = "", optional: Boolean = false) {
        val path = createPath(name, fieldName)

        val field =
            fieldMaker(path, optional, description, SimpleType.STRING)
        fields.add(field)
    }

    fun bool(fieldName: String, description: String = "", optional: Boolean = false) {
        val path = createPath(name, fieldName)

        val field =
            fieldMaker(path, optional, description, SimpleType.BOOLEAN)
        fields.add(field)
    }

    fun stringArray(fieldName: String, description: String = "", optional: Boolean = false) {
        val path = createPath(name, fieldName)

        val field =
            fieldMaker(path, optional, "string type. $description", JsonFieldType.ARRAY)
        fields.add(field)
    }

    fun boolArray(fieldName: String, description: String = "", optional: Boolean = false) {
        val path = createPath(name, fieldName)

        val field =
            fieldMaker(path, optional, "bool type. $description", JsonFieldType.ARRAY)
        fields.add(field)
    }

    fun numberArray(fieldName: String, description: String = "", optional: Boolean = false) {
        val path = createPath(name, fieldName)

        val field =
            fieldMaker(path, optional, "number type. $description", JsonFieldType.ARRAY)
        fields.add(field)
    }

    fun array(
        fieldName: String,
        optional: Boolean = false,
        description: String = "",
        func: JsonArray.() -> Unit
    ) {
        val path = createPath(name, fieldName)

        val section = sectionMaker(path, optional, description, JsonFieldType.ARRAY)
        fields.add(section)

        val array = JsonArray(fields, path)
        array.func()
    }

    fun objects(
        fieldName: String,
        optional: Boolean = false,
        description: String = "",
        func: JsonObject.() -> Unit
    ) {
        val path = createPath(name, fieldName)

        val section = sectionMaker(path, optional, description, JsonFieldType.OBJECT)
        fields.add(section)

        val objects = JsonObject(fields, path)
        objects.func()
    }
}
