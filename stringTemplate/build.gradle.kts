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
    annotation("com.yuk.common.stringtemplate.AllOpen")
}

dependencies {
    compileOnly("org.springframework.data:spring-data-jpa:2.5.3")
    compileOnly("jakarta.persistence:jakarta.persistence-api:2.2.3")

    testImplementation("org.springframework.boot:spring-boot-starter-data-jpa:2.5.3")
    testImplementation("org.springframework.boot:spring-boot-starter-test:2.5.3")
    testImplementation("org.mockito.kotlin:mockito-kotlin:4.0.0")
    testRuntimeOnly("mysql:mysql-connector-java:8.0.29")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
