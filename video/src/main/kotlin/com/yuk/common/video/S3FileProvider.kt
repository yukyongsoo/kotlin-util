package com.yuk.common.video

import org.springframework.core.io.InputStreamResource
import software.amazon.awssdk.core.sync.RequestBody
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.GetObjectRequest
import software.amazon.awssdk.services.s3.model.PutObjectRequest
import java.io.InputStream

class S3FileProvider(
    private val s3Client: S3Client,
    private val bucketName: String,
) : FileProvider {
    override fun getVideo(path: VideoPathKey): Video {
        val request = GetObjectRequest.builder()
            .bucket(bucketName)
            .key(path.path)
            .build()

        val response = s3Client.getObject(request)
        response.response().contentLength()

        return Video(
            response.response().contentLength(),
            InputStreamResource(response)
        )
    }

    override fun saveVideo(
        inputStream: InputStream,
        path: String
    ): VideoPathKey {
        val request = PutObjectRequest.builder()
            .bucket(bucketName)
            .key(path)
            .build()

        val response = s3Client.putObject(
            request,
            RequestBody.fromInputStream(inputStream, inputStream.available().toLong())
        )

        return VideoPathKey(path)
    }
}
