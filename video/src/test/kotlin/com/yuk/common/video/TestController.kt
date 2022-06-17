package com.yuk.common.video

import org.springframework.core.io.ByteArrayResource
import org.springframework.core.io.Resource
import org.springframework.core.io.support.ResourceRegion
import org.springframework.http.HttpRange
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody

@RestController
class TestController(
    private val videoService: VideoService
) {
    @GetMapping
    fun getTest(): Resource {
        val path = VideoPathKey("D:\\tmp\\movie.mp4")

        val resource = videoService.getVideo(path)

        return resource
    }

    @PostMapping
    fun saveTest(
        multipartFile: MultipartFile
    ) {
        videoService.saveVideo(multipartFile, "D:\\tmp\\movie.mp4")
    }
}
