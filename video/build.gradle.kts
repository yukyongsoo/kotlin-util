group = "com.yuk.common"
version = "0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework:spring-webmvc:5.3.9")

    testImplementation("org.springframework.boot:spring-boot-starter-web:2.5.3")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
