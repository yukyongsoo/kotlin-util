group = "com.yuk.common"
version = "0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework:spring-webmvc:6.2.12")

    implementation(platform("software.amazon.awssdk:bom:2.36.3"))
    implementation("software.amazon.awssdk:s3")

    testImplementation("org.springframework.boot:spring-boot-starter-web:3.5.7")
    testImplementation("org.springframework.boot:spring-boot-starter-test:3.5.7")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
