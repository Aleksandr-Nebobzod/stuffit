plugins {
    kotlin("jvm")
    application
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "top.smartable.stuffit"
version = "1.0.0"


val ktorVersion = "2.3.12"

dependencies {
    // Используем бандл для Ktor
    implementation(libs.bundles.ktor.server)

    // Остальные библиотеки из каталога
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.slf4j.simple)
    implementation(libs.kotlinx.datetime)

    // Обращение к общему модулю через Type-safe accessor
    implementation(projects.shared)
//    implementation("io.ktor:ktor-server-core:$ktorVersion")
//    implementation("io.ktor:ktor-server-netty:$ktorVersion")
//    implementation(libs.kotlinx.coroutines.core)
//    implementation("org.slf4j:slf4j-simple:2.0.9")
//    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.5.0")
//    implementation("io.ktor:ktor-server-content-negotiation:$ktorVersion")
//    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
//    implementation(project(":shared"))
}

application {
    mainClass.set("top.smartable.stuffit.AppKt")
}

tasks.shadowJar {
    archiveFileName.set("api-server-all.jar")
    mergeServiceFiles()
}
// ****************************************

// Перенос версии в билд
/*
val appVersion = project.findProperty("appVersion")?.toString() ?: "0.0.1"

val generateVersionFile = tasks.register("generateVersionFile") {
    // Подготавливаем путь к папке заранее
    val outputDir = layout.buildDirectory.dir("generated/version/kotlin")

    inputs.property("version", appVersion)
    outputs.dir(outputDir)

    doLast {
        // Используем outputDir напрямую, не вызывая функции проекта (file() или project)
        val buildInfoFile = outputDir.get().file("top/smartable/stuffit/BuildInfo.kt").asFile

        buildInfoFile.parentFile.mkdirs()
        buildInfoFile.writeText("""
            package top.smartable.stuffit
            
            object BuildInfo {
                const val VERSION = "$appVersion"
            }
        """.trimIndent())
    }
}

// Указываем Kotlin, что нужно использовать сгенерированные файлы
sourceSets.main {
    kotlin.srcDir(generateVersionFile)
}*/
