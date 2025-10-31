group = "com.yuk.common"
version = "0.2-SNAPSHOT"

plugins {
    val kotlinVersion = "2.2.21"

    kotlin("plugin.spring") version kotlinVersion
}

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("org.springframework:spring-context:6.2.12")
    implementation("org.springframework:spring-aop:6.2.12")
    implementation("org.aspectj:aspectjrt:1.9.24")
    implementation("org.aspectj:aspectjweaver:1.9.24")

    testImplementation("org.springframework.boot:spring-boot-starter-test:3.5.7")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
