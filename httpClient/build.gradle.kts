group = "com.yuk.common"
version = "0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework:spring-webflux:5.3.9")
    implementation("io.projectreactor.netty:reactor-netty-http:1.0.10")

    testImplementation("org.junit.jupiter:junit-jupiter:5.7.2")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
