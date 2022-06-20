package com.yuk.common.video

import org.springframework.core.io.InputStreamResource
import org.springframework.stereotype.Service
import java.io.InputStream

@Service
interface FileProvider {
    fun getVideo(path: VideoPathKey): InputStreamResource
    fun saveVideo(inputStream: InputStream, path: String): VideoPathKey
}
