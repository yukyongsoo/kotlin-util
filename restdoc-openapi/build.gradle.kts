group = "com.yuk.common"
version = "0.2-SNAPSHOT"

plugins {
    id("com.epages.restdocs-api-spec") version "0.19.4"
}

repositories {
    mavenCentral()
}

dependencies {
    // https://github.com/ePages-de/restdocs-api-spec
    implementation("com.epages:restdocs-api-spec-mockmvc:0.19.4")
    implementation("com.epages:restdocs-api-spec-webtestclient:0.19.4")
    compileOnly("org.springframework.restdocs:spring-restdocs-mockmvc:3.0.5")

    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc:3.0.5")
    testImplementation("org.springframework.boot:spring-boot-starter-test:3.5.7")
    testImplementation("org.springframework.boot:spring-boot-starter-web:3.5.7")
    testImplementation("org.springframework.boot:spring-boot-starter-webflux:3.5.7")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
