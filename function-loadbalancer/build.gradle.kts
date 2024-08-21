group = "com.yuk.common"
version = "0.1-SNAPSHOT"

plugins {
    val kotlinVersion = "2.0.0"

    kotlin("plugin.spring") version kotlinVersion
}

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("org.springframework:spring-context:5.3.20")
    implementation("org.springframework:spring-aop:5.3.20")
    implementation("org.aspectj:aspectjrt:1.9.6")
    implementation("org.aspectj:aspectjweaver:1.9.7")

    testImplementation("org.springframework.boot:spring-boot-starter-test:2.5.3") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
