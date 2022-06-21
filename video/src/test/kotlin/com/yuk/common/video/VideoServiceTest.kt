package com.yuk.common.video

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.io.File

@SpringBootTest
class VideoServiceTest {
    @Autowired
    lateinit var videoService: VideoService

    @Test
    fun createVideo() {
        val file = File("/Users/yug-yongsu/Downloads/test.mp4").inputStream()
        val pathKey = videoService.saveVideo(file, "temp/test.mp4")

        assert(pathKey.path == "temp/test.mp4")
    }

    @Test
    fun getVideo() {
        val resource = videoService.getVideo(VideoPathKey("temp/test.mp4"))

        assert(resource.size != 0L)
    }
}
