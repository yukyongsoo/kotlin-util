package com.yuk.common.video

import org.springframework.core.io.Resource
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class VideoService(
    private val fileProvider: FileProvider
) {
    fun getVideo(path: VideoPathKey): Resource {
        return fileProvider.getVideo(path)
    }

    fun saveVideo(multipartFile: MultipartFile, path: String): VideoPathKey {
        val savedPath = fileProvider.saveVideo(multipartFile.inputStream, path)

        return savedPath
    }
}