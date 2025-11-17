package com.yuk.common.openapidiff

import org.openapitools.openapidiff.core.OpenApiCompare
import org.openapitools.openapidiff.core.model.ChangedOpenApi
import org.openapitools.openapidiff.core.output.HtmlRender
import org.openapitools.openapidiff.core.output.MarkdownRender
import java.io.FileWriter
import java.io.OutputStreamWriter

class OpenApiDiff {
    fun diff(
        old: String,
        new: String,
    ) {
        val diff = OpenApiCompare.fromLocations(old, new)

        createFile(diff)
    }

    private fun createFile(diff: ChangedOpenApi) {
        val html =
            HtmlRender(
                "Changelog",
                "http://deepoove.com/swagger-diff/stylesheets/demo.css",
            ).render(diff, FileWriter("NewApi.html"))

        val markdown = MarkdownRender().render(diff, FileWriter("NewApi.md"))
    }
}
