plugins {
    val kotlinVersion = "1.5.21"

    kotlin("jvm") version kotlinVersion
    idea

    id("org.jmailen.kotlinter") version "3.2.0"
    id("maven-publish")

}

allprojects {
    group = "com.yuk.common"

    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jmailen.kotlinter")
    apply(plugin = "maven-publish")

    dependencies {
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    }

    java.sourceCompatibility = JavaVersion.VERSION_1_8

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "1.8"
        }
    }

    tasks.withType<Wrapper> {
        gradleVersion = "7.1.1"
    }
}
