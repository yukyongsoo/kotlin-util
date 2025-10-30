group = "com.yuk.common"
version = "0.2-SNAPSHOT"

repositories {
    mavenCentral()
}

plugins {
    val kotlinVersion = "2.2.21"

    kotlin("plugin.spring") version kotlinVersion
    kotlin("plugin.jpa") version kotlinVersion
    kotlin("plugin.allopen") version kotlinVersion

    id("com.google.devtools.ksp") version "2.2.21-2.0.4"
}

allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("org.springframework.stereotype.Service")
}

dependencies {
    implementation("io.github.openfeign.querydsl:querydsl-jpa:6.12")
    implementation("org.springframework.data:spring-data-jpa:3.5.5")
    implementation("com.blazebit:blaze-persistence-integration-querydsl-expressions-jakarta:1.6.17")
    implementation("com.blazebit:blaze-persistence-integration-hibernate-6.2:1.6.17")

    kspTest("io.github.openfeign.querydsl:querydsl-ksp-codegen:6.12")
    annotationProcessor("io.github.openfeign.querydsl:querydsl-apt:6.12:jakarta")

    testImplementation("org.springframework.boot:spring-boot-starter-data-jpa:3.5.7")
    testImplementation("org.springframework.boot:spring-boot-starter-test:3.5.7")
    testImplementation("org.springframework.boot:spring-boot-testcontainers:3.5.7")
    testImplementation("org.testcontainers:junit-jupiter:1.21.3")
    testImplementation("org.testcontainers:testcontainers-mysql:2.0.1")

    testRuntimeOnly("com.mysql:mysql-connector-j:9.5.0")
}

idea {
    module {
        val srcPath = "test"
        val kaptMain = file("build/generated/source/kapt/$srcPath")
        sourceDirs.add(kaptMain)
        generatedSourceDirs.add(kaptMain)
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
