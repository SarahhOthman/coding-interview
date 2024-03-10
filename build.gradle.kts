plugins {
    kotlin("jvm") version "1.9.23"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {


    // JUnit Jupiter API for testing
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")

    // Mockito for mocking objects
    testImplementation("org.mockito:mockito-core:4.6.1")
    testImplementation("org.mockito.kotlin:mockito-kotlin:4.0.0")

    testImplementation("org.mockito:mockito-core:4.6.1") // Mockito for mocking
    testImplementation("org.mockito.kotlin:mockito-kotlin:4.0.0") // Kotlin support for Mockito

}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}

application {
    mainClass.set("MainKt")
}