plugins {
    val kotlinVersion = "2.0.0"

    kotlin("jvm") version kotlinVersion
    kotlin("kapt") version kotlinVersion

    idea

    id("org.jmailen.kotlinter") version "4.4.1"
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

        testImplementation("org.junit.jupiter:junit-jupiter:5.11.0")
    }

    java.sourceCompatibility = JavaVersion.VERSION_1_8

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "1.8"
        }
    }

    tasks.withType<Wrapper> {
        gradleVersion = "8.3"
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    testImplementation("org.junit.jupiter:junit-jupiter:5.11.0")
}
