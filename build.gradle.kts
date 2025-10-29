import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    val kotlinVersion = "2.2.21"

    kotlin("jvm") version kotlinVersion
    kotlin("kapt") version kotlinVersion

    idea

    id("org.jmailen.kotlinter") version "5.2.0"
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
    apply(plugin = "kotlin-kapt")
    apply(plugin = "idea")

    dependencies {
        implementation("org.jetbrains.kotlin:kotlin-reflect")

        testImplementation("org.junit.jupiter:junit-jupiter:6.0.0")
        testImplementation("org.junit.platform:junit-platform-launcher:6.0.0")
    }

    java.sourceCompatibility = JavaVersion.VERSION_17

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        compilerOptions {
            freeCompilerArgs.set(listOf("-Xjsr305=strict"))
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }

    tasks.withType<Wrapper> {
        gradleVersion = "9.1.0"
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    testImplementation("org.junit.jupiter:junit-jupiter:6.0.0")
    testImplementation("org.junit.platform:junit-platform-launcher:6.0.0")
}
