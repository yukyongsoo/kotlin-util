package com.yuk.common.video

import org.springframework.core.io.Resource
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
class TestController(
    private val videoService: VideoService
) {
    @GetMapping
    fun getTest(): ResponseEntity<Resource> {
        val path = VideoPathKey("temp/test.mp4")
        // val path = VideoPathKey("/Users/yug-yongsu/Downloads/test.mp4")
        val video = videoService.getVideo(path)

        val headers = HttpHeaders()
        headers.contentLength = video.size
        // You need play partial video
        headers.add("accept-ranges", "bytes")

        return ResponseEntity.ok().headers(headers).body(video.resource)
    }

    @PostMapping
    fun saveTest(
        multipartFile: MultipartFile
    ) {
        videoService.saveVideo(multipartFile.inputStream, "D:\\tmp\\movie.mp4")
    }
}
