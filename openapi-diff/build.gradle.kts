group = "com.yuk.common"
version = "0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.openapitools.openapidiff:openapi-diff-core:2.0.1")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.0")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
