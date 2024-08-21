package com.yuk.common.video

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.s3.S3Client
import java.net.URI

@SpringBootApplication
open class TestApplication

fun main() {
    SpringApplication.run(TestApplication::class.java)
}

@Configuration
open class TestConfiguration {
    @Bean
    open fun getS3Client(): S3Client =
        S3Client
            .builder()
            .endpointOverride(URI("http://localhost:4566"))
            .region(Region.AP_NORTHEAST_2)
            .build()

    @Bean
    open fun getFileProvider(s3Client: S3Client): FileProvider {
//        return LocalFileProvider()
        return S3FileProvider(s3Client, "test-bucket")
    }
}
