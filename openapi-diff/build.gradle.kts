group = "com.yuk.common"
version = "0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.openapitools.openapidiff:openapi-diff-core:2.1.4")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
