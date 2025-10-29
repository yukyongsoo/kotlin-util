group = "com.yuk.common"
version = "0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jeasy:easy-random-core:5.0.0")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
