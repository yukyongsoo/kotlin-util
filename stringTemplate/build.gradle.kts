group = "com.yuk.common"
version = "0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

plugins {
    val kotlinVersion = "1.5.21"

    kotlin("plugin.spring") version kotlinVersion
    kotlin("plugin.jpa") version kotlinVersion
    kotlin("plugin.allopen") version kotlinVersion
}

allOpen {
    annotation("javax.persistence.Entity")
}

dependencies {
    compileOnly("org.springframework.data:spring-data-jpa:2.5.3")
    compileOnly("jakarta.persistence:jakarta.persistence-api:2.2.3")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
