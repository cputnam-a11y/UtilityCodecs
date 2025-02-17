plugins {
    id("java")
    kotlin("jvm") version "2.1.10"
}
repositories {
    exclusiveContent {
        forRepositories(maven("https://libraries.minecraft.net") {
            name = "Minecraft Maven"
        })
        filter {
            includeGroup("com.mojang")
        }
    }
    mavenCentral()
    maven("https://repo1.maven.org/maven2/")
}

dependencies {
    implementation("com.mojang:datafixerupper:8.0.16")
    implementation("org.slf4j:slf4j-api:2.0.16")
    implementation("org.apache.commons:commons-lang3:3.17.0")
    testImplementation("org.junit.jupiter:junit-jupiter:5.7.1")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}
tasks.named<Test>("test") {
    useJUnitPlatform()

    maxHeapSize = "1G"

    testLogging {
        events("passed")
    }
}