group = "com.yuk.common"
version = "0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.testcontainers:junit-jupiter:1.16.2")
    implementation("org.testcontainers:localstack:1.17.1")
    implementation("org.testcontainers:mysql:1.17.1")
    implementation("org.junit.jupiter:junit-jupiter:5.7.2")
    implementation("org.testcontainers:elasticsearch:1.17.1")
    implementation("org.testcontainers:kafka:1.17.1")

    implementation(platform("com.amazonaws:aws-java-sdk-bom:1.12.133"))
    implementation("com.amazonaws:aws-java-sdk-core:1.12.133")
    implementation("com.amazonaws:aws-java-sdk-kinesis")
    implementation("com.amazonaws:aws-java-sdk-iam")
    implementation("com.amazonaws:aws-java-sdk-sqs")

    testRuntimeOnly("mysql:mysql-connector-java:8.0.29")
    testImplementation("ch.qos.logback:logback-classic:1.2.11")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
