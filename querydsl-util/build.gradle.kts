group = "com.yuk.common"
version = "0.2-SNAPSHOT"

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
    annotation("org.springframework.stereotype.Service")
}

dependencies {
    implementation("com.querydsl:querydsl-jpa:5.0.0")
    implementation("org.springframework.data:spring-data-jpa:2.5.3")
    implementation("com.blazebit:blaze-persistence-integration-querydsl-expressions:1.6.6")
    implementation("com.blazebit:blaze-persistence-integration-hibernate-5.4:1.6.6")

    kaptTest("com.querydsl:querydsl-apt:5.0.0:jpa")

    testImplementation("org.springframework.boot:spring-boot-starter-data-jpa:2.5.3")
    testImplementation("org.springframework.boot:spring-boot-starter-test:2.5.3") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
    testImplementation(project(":testContainer"))

    testRuntimeOnly("mysql:mysql-connector-java:8.0.29")
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
