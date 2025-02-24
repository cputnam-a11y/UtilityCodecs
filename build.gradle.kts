import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("java")
    kotlin("jvm") version "2.1.10"
    id("maven-publish")
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
kotlin {
    compilerOptions {
        jvmToolchain {
            JavaVersion.VERSION_21

        }
        target { jvmTarget.set(JvmTarget.JVM_21) }
    }
}
tasks.withType<JavaCompile> {
    options.release = 21
    targetCompatibility = "21"
}
tasks.named<Test>("test") {
    useJUnitPlatform()

    maxHeapSize = "1G"

    testLogging {
        events("passed")
    }
}
publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            groupId = "io.github.cputnama11y"
            artifactId = "utilitycodecs"
            version = "0.0.2"
        }

    }
    repositories {
        mavenLocal()
    }
}