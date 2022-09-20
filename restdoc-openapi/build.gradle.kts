group = "com.yuk.common"
version = "0.1-SNAPSHOT"

plugins {
    id("com.epages.restdocs-api-spec") version "0.16.2"
}

repositories {
    mavenCentral()
}

dependencies {
    // https://github.com/ePages-de/restdocs-api-spec
    implementation("com.epages:restdocs-api-spec-mockmvc:0.16.2")
    implementation("com.epages:restdocs-api-spec-webtestclient:0.16.2")

    testImplementation("org.springframework.boot:spring-boot-starter-test:2.7.0")
    testImplementation("org.springframework.boot:spring-boot-starter-web:2.7.0")
    testImplementation("org.springframework.boot:spring-boot-starter-webflux:2.7.0")

}

tasks.withType<Test> {
    useJUnitPlatform()
}
