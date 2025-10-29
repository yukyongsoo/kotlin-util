group = "com.yuk.common"
version = "0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework:spring-webflux:6.2.12")
    implementation("io.projectreactor.netty:reactor-netty-http:1.2.11")

    testImplementation("org.springframework.boot:spring-boot-starter-test:3.5.7")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
