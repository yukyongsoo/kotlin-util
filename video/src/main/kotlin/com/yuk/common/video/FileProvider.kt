package com.yuk.common.video

import org.springframework.core.io.InputStreamResource
import java.io.InputStream

interface FileProvider {
    fun getVideo(path: VideoPathKey): InputStreamResource
    fun saveVideo(inputStream: InputStream, path: String): VideoPathKey
}