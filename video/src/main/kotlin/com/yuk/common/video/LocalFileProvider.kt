package com.yuk.common.video

import org.springframework.core.io.InputStreamResource
import java.io.File
import java.io.InputStream

class LocalFileProvider : FileProvider {
    override fun getVideo(path: VideoPathKey): Video {
        val file = File(path.path)

        return Video(
            file.length(),
            InputStreamResource(file.inputStream()),
        )
    }

    override fun saveVideo(
        inputStream: InputStream,
        path: String,
    ): VideoPathKey {
        File(path).outputStream().use {
            inputStream.copyTo(it)
        }

        return VideoPathKey(path)
    }
}
