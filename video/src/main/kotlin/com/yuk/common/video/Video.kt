package com.yuk.common.video

import org.springframework.core.io.Resource

data class Video(
    val size: Long,
    val resource: Resource,
)
