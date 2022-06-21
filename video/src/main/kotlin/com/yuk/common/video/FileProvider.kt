package com.yuk.common.video

import org.springframework.stereotype.Service
import java.io.InputStream

@Service
interface FileProvider {
    fun getVideo(path: VideoPathKey): Video
    fun saveVideo(inputStream: InputStream, path: String): VideoPathKey
}
