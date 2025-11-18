group = "com.yuk.common"
version = "0.1-SNAPSHOT"

plugins {
    val kotlinVersion = "2.2.21"

    kotlin("plugin.spring") version kotlinVersion
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.assertj:assertj-core:3.26.3")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
