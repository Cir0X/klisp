import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val kotlinVersion = "1.3.50"

plugins {
    kotlin("jvm") version "1.3.50"
    application
}

group = "de.cir0x"
version = "1.0-SNAPSHOT"

application {
    mainClassName = "ReplKt"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.5.2")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

val run by tasks.getting(JavaExec::class) {
    standardInput = System.`in`
}
