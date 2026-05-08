plugins {
    kotlin("jvm")
    application
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "top.smartable.stuffit"
version = "1.0.0"

repositories {
    mavenCentral()
}

val ktorVersion = "2.3.7"

dependencies {
    implementation("io.ktor:ktor-server-core:$ktorVersion")
    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("org.slf4j:slf4j-simple:2.0.9")
}

application {
    mainClass.set("top.smartable.stuffit.AppKt")
}

tasks.shadowJar {
    archiveFileName.set("api-server-all.jar")
    mergeServiceFiles()
}