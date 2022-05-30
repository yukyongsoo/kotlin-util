group = "com.yuk.common"
version = "0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jeasy:easy-random-core:4.3.0")
    implementation("com.github.javafaker:javafaker:1.0.2")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
