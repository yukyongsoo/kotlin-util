package com.yuk.common.video

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.s3.S3Client

@SpringBootApplication
open class TestApplication

fun main() {
    SpringApplication.run(TestApplication::class.java)
}

@Configuration
open class TestConfiguration {
    @Bean
    open fun getS3Client(): S3Client {
        return S3Client.builder()
            .region(Region.AP_NORTHEAST_2)
            .build()
    }

    @Bean
    open fun getFileProvider(s3Client: S3Client): FileProvider {
        return S3FileProvider(s3Client, "test-bucket")
    }
}
