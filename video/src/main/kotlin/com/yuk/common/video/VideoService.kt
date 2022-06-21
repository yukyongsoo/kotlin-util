package com.yuk.common.video

import org.springframework.stereotype.Service
import java.io.InputStream

@Service
class VideoService(
    private val fileProvider: FileProvider
) {
    fun getVideo(path: VideoPathKey): Video {
        return fileProvider.getVideo(path)
    }

    fun saveVideo(file: InputStream, path: String): VideoPathKey {
        val savedPath = fileProvider.saveVideo(file, path)

        return savedPath
    }
}
